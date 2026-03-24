package com.mtech.coding.epam;
public class ApiController {

	private final FixedWindowRateLimiter fixedWindow = new FixedWindowRateLimiter(
			5, 5);

	private final TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(
			5, 5); // 5 requests per second

	public String getTokenData() {
		return rateLimiter.allowRequest() ? "Success" : "Too Many Requests";
	}

	public String getWindowData() {

		return fixedWindow.allowRequest() ? "Success" : "Too Many Requests";
	}

	public static void main(String[] args) throws InterruptedException {
		ApiController controller = new ApiController();
		for (int i = 0; i < 10; i++) {
			System.out.println(controller.getTokenData());
		}
		System.out.println("======================");
		for (int i = 0; i < 10; i++) {
			System.out.println(controller.getWindowData());
		}
	}
}