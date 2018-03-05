package lt.telesoft.restController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.telesoft.gildedrose.Item;
import lt.telesoft.repository.TelesoftItemRepository;
import lt.telesoft.restController.json.JsonResponse;
import lt.telesoft.restController.json.ResponseStatus;


@RestController
@RequestMapping("/api/find")
public class TelesoftRestSearchController
{
	private final static Logger LOG = LoggerFactory.getLogger(TelesoftRestSearchController.class);

	@Autowired
	private TelesoftItemRepository telesoftItemRepository;

	@RequestMapping(value = "/one", method = RequestMethod.GET)
	private JsonResponse findById(@RequestParam Long id)
	{
		LOG.info("FindById :" + id);
		final Item item = telesoftItemRepository.findOne(id);
		if (item != null)
		{
			return new JsonResponse(ResponseStatus.SUCCESS, item, "Search all Items");
		}
		return new JsonResponse(ResponseStatus.ERROR, null, "Not found item");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	private JsonResponse findAll()
	{
		LOG.info("Search all Items");
		final List<Item> itemList = telesoftItemRepository.findAll();
		if (itemList.size() > 0)
		{
			return new JsonResponse(ResponseStatus.SUCCESS, itemList, "Search all Items");
		}
		return new JsonResponse(ResponseStatus.ERROR, null, "Not found item's");
	}

	@RequestMapping(value = "/byName", method = RequestMethod.GET)
	private JsonResponse findByName(@RequestParam final String name)
	{
		LOG.info("Search Items By Name: " + name);
		List<Item> itemList = telesoftItemRepository.findByName(name);
		if (itemList.size() > 0)
		{
			return new JsonResponse(ResponseStatus.SUCCESS, itemList, "Search By name: " + name);
		}
		itemList = telesoftItemRepository.findByNameIgnoreCaseContaining(name);
		if (itemList.size() > 0)
		{
			return new JsonResponse(ResponseStatus.ERROR, "maybe you lookig for: " + itemList.get(0).getName(),
					"Searching By name: " + name);
		}
		return new JsonResponse(ResponseStatus.ERROR, null, "Searching By name: " + name);

	}
}
