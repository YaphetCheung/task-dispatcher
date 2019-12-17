package priv.yanfei.task.dispatcher;

import java.util.List;

public abstract class AbstractTaskLoader<T extends AbstractTask> {

    protected abstract List<T> loadTodoTasks(int size);
}
