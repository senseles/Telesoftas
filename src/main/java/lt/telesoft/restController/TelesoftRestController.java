package lt.telesoft.restController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.telesoft.gildedrose.Item;
import lt.telesoft.repository.TelesoftItemRepository;


@RestController
@RequestMapping("/api")
public class TelesoftRestController
{
	private final static Logger LOG = LoggerFactory.getLogger(TelesoftRestController.class);

	private static final String CREATE_DATA = "/createGildedRoseItem";
	@Autowired
	private TelesoftItemRepository telesoftItemRepository;

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
		Item[] items = new Item[] { new Item(name, sellIn, quality) };
		return telesoftItemRepository.save(items[0]).getId();
	}

}
