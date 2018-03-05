package gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

import lt.telesoft.gildedrose.GildedRoseHelper;
import lt.telesoft.gildedrose.Item;


public class GildedRoseTest
{

	@Test
	public void foo()
	{
		Item[] items = new Item[] { new Item("foo", 0, 0) };
		final Item item = GildedRoseHelper.getUpdatedGildedRose(items);
		assertEquals("foo", item.name);
	}

}
