package lt.telesoft.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GildedRoseController
{
	private final static Logger LOG = LoggerFactory.getLogger(GildedRoseController.class);

	public static Item getUpdatedGildedRose(final Item[] items)
	{
		LOG.info("Gilded Rose update: " + items);
		GildedRose gildedRose = new GildedRose(items);
		gildedRose.updateQuality();
		return gildedRose.getItem(items[0].name);
	}
}
