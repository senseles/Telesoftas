package lt.telesoft.restController.json;

public final class JsonResponse
{
	private final ResponseStatus status;
	private final Object data;
	private final String message;

	public JsonResponse(final ResponseStatus status, final Object data, final String message)
	{
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public ResponseStatus getStatus()
	{
		return status;
	}

	public Object getData()
	{
		return data;
	}

	public String getMessage()
	{
		return message;
	}

	public static JsonResponse success(final Object data)
	{
		return JsonResponse.success(data, null);
	}

	public static JsonResponse success(final Object data, final String message)
	{
		return new JsonResponse(ResponseStatus.SUCCESS, data, message);
	}

	public static JsonResponse error(final String message)
	{
		return JsonResponse.error(null, message);
	}

	public static JsonResponse error(final Object data, final String message)
	{
		return new JsonResponse(ResponseStatus.ERROR, data, message);
	}
}
