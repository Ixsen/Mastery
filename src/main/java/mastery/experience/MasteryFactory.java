package mastery.experience;

import java.util.concurrent.Callable;

/**
 * Created by Granis on 16/03/2018.
 */
public class MasteryFactory implements Callable<MasteryPersistenceManager> {

    @Override
    public MasteryPersistenceManager call() throws Exception {
	return new MasteryPersistenceManager();
    }

}
