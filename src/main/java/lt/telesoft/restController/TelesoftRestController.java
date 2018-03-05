package lt.telesoft.restController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.telesoft.gildedrose.GildedRoseHelper;
import lt.telesoft.gildedrose.Item;
import lt.telesoft.repository.TelesoftItemRepository;


@RestController
@RequestMapping("/api")
public class TelesoftRestController
{
	private final static Logger LOG = LoggerFactory.getLogger(TelesoftRestController.class);

	private static final String GENERATE_TESTING_DATA = "/generateTestingData";
	private static final String UPDATE_DATA = "/updateGildedRoseItem";
	private static final String CREATE_DATA = "/createGildedRoseItem";
	private static final String REDIRECT_HOMEPAGE = "/";

	@Value("${testing.data.names}")
	private String testData;

	@Value("${testing.data.sellIn.max}")
	private int sellInMax;

	@Value("${testing.data.quality.max}")
	private int qualityMax;

	@Autowired
	private TelesoftItemRepository telesoftItemRepository;

	@RequestMapping(value = GENERATE_TESTING_DATA, method = RequestMethod.POST)
	private void generateTestingData(final HttpServletResponse response) throws IOException
	{
		final Random rand = new Random();
		Arrays.asList(testData.split(",")).forEach(name ->
		{
			saveGildedRoseItem(name, rand.nextInt(sellInMax), rand.nextInt(qualityMax));
		});
		LOG.info("Created Testing Data");
		response.sendRedirect(REDIRECT_HOMEPAGE);
	}

	@RequestMapping(value = UPDATE_DATA, method = RequestMethod.POST)
	private void updateGildedRoseItem(final HttpServletResponse response) throws IOException
	{
		final List<Item> items = telesoftItemRepository.findAll();
		items.stream().map(item -> GildedRoseHelper.getUpdatedGildedRose(new Item[] { item }))
				.collect(Collectors.toList()).toString();
		response.sendRedirect(REDIRECT_HOMEPAGE);
	}

	@RequestMapping(value = CREATE_DATA, method = RequestMethod.POST)
	private String createGildedRoseItem(@RequestParam final String name, @RequestParam final int sellIn,
			@RequestParam final int quality)
	{
		LOG.info("Create New Item {}" + name);
		final Long itemId = saveGildedRoseItem(name, sellIn, quality);
		return "Success saved: " + itemId;
	}

	private Long saveGildedRoseItem(final String name, final int sellIn, final int quality)
	{
		final Item[] items = new Item[] { new Item(name, sellIn, quality) };
		return telesoftItemRepository.save(items[0]).getId();
	}

}
