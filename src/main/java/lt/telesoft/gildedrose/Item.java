package lt.telesoft.gildedrose;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Item
{
	@Id
	@GeneratedValue
	private Long id;

	public String name;

	public int sellIn;

	public int quality;

	public Item()
	{
	}

	public Item(String name, int sellIn, int quality)
	{
		this.name = name;
		this.sellIn = sellIn;
		this.quality = quality;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSellIn()
	{
		return sellIn;
	}

	public void setSellIn(int sellIn)
	{
		this.sellIn = sellIn;
	}

	public int getQuality()
	{
		return quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
	}

	@Override
	public String toString()
	{
		return this.name + ", " + this.sellIn + ", " + this.quality;
	}
}
