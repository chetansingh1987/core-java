package practical;

import java.util.function.BiFunction;

public class StreamPractical3 {

	public void test() {
		PriceService p = new PriceService(new NewReleasePriceRepo());
		p.computePrice(Movie.Type.REGULAR, 1);
	}
	
}

class Movie {
	
    enum Type {
            REGULAR(PriceService::computeRegularPrice),
            NEW_RELEASE(PriceService::computeNewReleasePrice),
            CHILDREN(PriceService::computeChildrenPrice) ;
            BiFunction<PriceService, Integer, Integer> algo ;
            Type(BiFunction<PriceService, Integer, Integer> algo) {
            	this.algo=algo;
            }
    }
    private final Type type;
    public Movie(Type type) {
            this.type = type;
    }
    public int computePrice(int days) {
            switch (type) {
            case REGULAR: return days + 1;
            case NEW_RELEASE: return days * 2;
            case CHILDREN: return 5;
            default: throw new IllegalArgumentException(); // Always have this here!
            }
    }
}

class NewReleasePriceRepo {
	public int getFactor() {
		return 10;
	}
}
class PriceService {
    private final NewReleasePriceRepo repo;
    public PriceService(NewReleasePriceRepo repo) {
            this.repo = repo;
    }
    public int computeNewReleasePrice(int days) {
            return (int) (days * repo.getFactor());
    }
    public int computeRegularPrice(int days) {
            return days + 1;
    }
    public int computeChildrenPrice(int days) {
            return 5;
    }
    public int computePrice(Movie.Type type, int days) {
            switch (type) {
            case REGULAR: return computeRegularPrice(days);
            case NEW_RELEASE: return computeNewReleasePrice(days);
            case CHILDREN: return computeChildrenPrice(days);
            default: throw new IllegalArgumentException();
            }
    }
}
