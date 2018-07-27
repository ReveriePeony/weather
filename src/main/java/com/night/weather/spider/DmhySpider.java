package com.night.weather.spider;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.night.weather.entity.acg.AcgClass;
import com.night.weather.entity.acg.AcgResources;
import com.night.weather.service.acg.AcgClassService;
import com.night.weather.service.acg.AcgResourcesService;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月22日
 */
@Component
public class DmhySpider {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AcgClassService acgClassService;

	@Autowired
	private AcgResourcesService acgResourcesService;

	private DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	private ZoneId ZONE = ZoneId.systemDefault();

	private static final String HOST = "https://share.dmhy.org";

	public void start() {
		Wrapper<AcgClass> w = new EntityWrapper<>();
		w.eq("status", 1);
		List<AcgClass> classes = acgClassService.selectList(w);
		classes.forEach(c -> {
			Wrapper<AcgResources> wrapper = new EntityWrapper<>();
			wrapper.orderBy("resourcesmillis", false).eq("classid", c.getId());
			List<AcgResources> resources = acgResourcesService.selectList(wrapper);
			long millisecond = 0l;
			if (resources.size() > 0 && resources.get(0).getResourcesmillis() != null)
				millisecond = resources.get(0).getResourcesmillis();
			List<String> titles = new ArrayList<>();
			resources.forEach(t -> titles.add(t.getName()));
			List<Map<String, String>> resourcesList = collectResourcesURLList(c.getClassurl(), titles, millisecond);
			List<AcgResources> ars = collectResources(resourcesList, c.getId(), titles);
			// ars.forEach(a -> {
			// System.err.println(a);
			// });
			System.err.println(ars.size());
			if (ars.size() > 0) {
				AcgClass ac = new AcgClass();
				ac.setId(c.getId());
				ac.setUpdatequantity(ars.size());
				ac.setUpdatetime(System.currentTimeMillis());
				acgClassService.updateQuantity(ac, true);
				acgResourcesService.insertBatch(ars);
			}
		});
	}

	private List<Map<String, String>> collectResourcesURLList(String url, List<String> titles, Long millisecond) {
		List<Map<String, String>> maps = new ArrayList<>();
		System.err.println(HOST + url);
		ResponseEntity<String> forEntity = restTemplate.getForEntity(HOST + url, String.class, new Object[] {});
		String body = forEntity.getBody();
		Matcher matcher = Pattern.compile(
				"<table\\s*style=\"width:100%;\"\\s*class=\"tablesorter\"\\s*id=\"topic_list\"\\s*cellpadding=\"0\"\\s*cellspacing=\"1\"\\s*border=\"0\"\\s*width=\"\"\\s*frame=\"void\">[\\s\\S]*</table>")
				.matcher(body);
		boolean find = matcher.find();
		System.err.println(find);
		if (find) {
			String table = matcher.group();
			Matcher matcher2 = Pattern.compile("<tr\\s*class=\"[evnod]*\">[\\s\\S]*?</tr>").matcher(table);
			while (matcher2.find()) {
				String s = matcher2.group();
				Matcher mTime = Pattern.compile("\\d{4}\\/\\d{2}\\/\\d{2}\\s+\\d{2}\\:\\d{2}").matcher(s);
				mTime.find();
				String time = mTime.group();
				LocalDateTime localDateTime = LocalDateTime.parse(time, DTF);
				Instant instant = localDateTime.atZone(ZONE).toInstant();
				if (instant.toEpochMilli() >= millisecond) {
					Matcher mTitle = Pattern.compile("_blank\"\\s*>[\\s\\S]+?</a>").matcher(s);
					mTitle.find();
					String title = mTitle.group().replace("</a>", "").replaceAll("_blank\"\\s*>", "")
							.replaceAll("\r|\n", "").trim();
					if (!titles.contains(title)) {
						Matcher mLink = Pattern.compile("\\/topics\\/view\\/[\\s\\S]+\\.html").matcher(s);
						mLink.find();
						String link = mLink.group();
						Map<String, String> m = new HashMap<String, String>();
						m.put(time, link);
						maps.add(m);
					}
				}
			}
		}
		return maps;
	}

	private List<AcgResources> collectResources(List<Map<String, String>> resourcesURLList, Long classid,
			List<String> titles) {
		List<AcgResources> ars = new ArrayList<>();
		System.err.println(resourcesURLList.size());
		resourcesURLList.forEach(url -> {
			url.forEach((k, v) -> {
				ResponseEntity<String> forEntity = restTemplate.getForEntity(HOST + v, String.class, new Object[] {});
				if (forEntity.getStatusCodeValue() == 200) {
					String body = forEntity.getBody();
					Matcher nameMatcher = Pattern.compile("<h3>[\\s\\S]*?</h3>").matcher(body);
					String name = "";
					if (nameMatcher.find()) {
						name = nameMatcher.group().replace("<h3>", "").replace("</h3>", "");
						if (!titles.contains(name)) {
							AcgResources ar = new AcgResources();
							ar.setClassid(classid);
							ar.setResourcestime(k.replace("/", "-").replace("/", "-") + ":00");
							Long milli = LocalDateTime.parse(k, DTF).atZone(ZONE).toInstant().toEpochMilli();
							ar.setResourcesmillis(milli);
							ar.setName(name);
							Matcher publisherMatcher = Pattern.compile("<p>發佈人：[\\s\\S]+?</a>").matcher(body);
							if (publisherMatcher.find()) {
								ar.setPublisher(publisherMatcher.group().replaceAll("<[\\s\\S]+?>|發佈人：", ""));
							}
							Matcher sizeMatcher = Pattern.compile("\\d+[\\.\\d+]*[G|M]+B").matcher(body);
							if (sizeMatcher.find())
								ar.setSize(sizeMatcher.group());
							Matcher torrentMatcher = Pattern.compile("\\/\\/dl\\.dmhy\\.org[\\s\\S]+?\\.torrent")
									.matcher(body);
							if (torrentMatcher.find())
								ar.setTorrent("https:" + torrentMatcher.group());
							Matcher magnetMatcher = Pattern
									.compile("magnet\\:\\?xt\\=urn\\:btih\\:([\\s\\S]{32}|[\\s\\S]{40})").matcher(body);
							if (magnetMatcher.find())
								ar.setMagnet(magnetMatcher.group());
							ars.add(ar);
						}
					}
				}
			});
		});
		return ars;
	}

}
