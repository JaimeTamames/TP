package observable;

public interface Observable<T> {

    public void addObservador(T o);

    public void removeObservador(T o);

}
