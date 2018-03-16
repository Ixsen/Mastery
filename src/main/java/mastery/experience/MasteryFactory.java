package mastery.experience;

import java.util.concurrent.Callable;

/**
 * Created by Granis on 16/03/2018.
 */
public class MasteryFactory implements Callable<PlayerExperience> {

    @Override
    public PlayerExperience call() throws Exception {
        return new PlayerExperience();
    }

}
