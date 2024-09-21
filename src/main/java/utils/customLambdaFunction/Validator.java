package utils.customLambdaFunction;

@FunctionalInterface
public interface Validator<T,S,U> {
    boolean isValid(T t, S s,U u);
}
