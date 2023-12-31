package grup47;



public class Resource {

	String resourceName;
	int resourceCount;
	int available;

	public Resource(String resourceName, int resourceCount) {

		this.resourceName = resourceName;
		this.resourceCount = resourceCount;
		available = resourceCount;

	}

	public void allocate(int requestedResource) {

		if (isAllocatable(requestedResource)) {
			available -= requestedResource;
		}

	}

	public void release(int releasedResource) {

		available += releasedResource;

	}

	public boolean isAllocatable(int requestedResource) {

		if (requestedResource <= available && isAvailable())
			return true;

		return false;
	}

	public boolean isAvailable() {
		
		if (available > 0)
			return true;
		
		return false;
	}

}
