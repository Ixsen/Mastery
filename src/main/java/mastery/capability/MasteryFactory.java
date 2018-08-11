package mastery.capability;

import java.util.concurrent.Callable;

/**
 * Created by Granis on 16/03/2018.
 */
public class MasteryFactory implements Callable<IMastery> {

	@Override
	public IMastery call() throws Exception {
		return new Mastery();
	}
}
