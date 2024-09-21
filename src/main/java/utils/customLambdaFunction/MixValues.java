package utils.customLambdaFunction;

@FunctionalInterface
public interface MixValues<T,S,U,R>{
    R alterValue(T t,S s,U u);
}
