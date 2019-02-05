[![Build Status](https://travis-ci.com/mtumilowicz/java11-reflection-invoking-methods.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-reflection-invoking-methods)

# java11-reflection-invoking-methods

Referring my other github projects concerning constructors and
reflection could be useful:
* https://github.com/mtumilowicz/java11-reflection-executables
* https://github.com/mtumilowicz/java11-reflection-methods

# preface
Using reflection, we can invoke methods of an object.

To invoke method using reflection, we have to:
1. Get reference to the method: https://github.com/mtumilowicz/java11-reflection-methods
1. Use the `public Object invoke(Object obj, Object... args)` of the `Method` class:
    * `obj` the object the underlying method is invoked from
    * `args` the arguments used for the method call
    * exceptions:
        * `IllegalAccessException` - if the underlying
          method is inaccessible (enforcing Java language 
          access control)
        * `IllegalArgumentException` - if the method is an
          instance method and the specified object argument
          is not an instance of the class or interface
          declaring the underlying method (or of a subclass
          or implementor thereof); if the number of actual
          and formal parameters differ; if an unwrapping
          conversion for primitive arguments fails; or if,
          after possible unwrapping, a parameter value
          cannot be converted to the corresponding formal
          parameter type by a method invocation conversion.
        * `InvocationTargetException` - if the underlying method
          throws an exception.
        * `NullPointerException` - if the specified object is null
          and the method is an instance method.
        * `ExceptionInInitializerError` if the initialization
          provoked by this method fails.
    * **we can invoke only that methods that we can call 
    with regular java code (otherwise `IllegalAccessException`), 
    we can bypass it using**: https://github.com/mtumilowicz/java11-deep-reflection
# project description
We will show how to invoke method using reflection.
```
class X {
    String concat(String string, int i) {
        return string + i;
    }
}
```
and invocation:
```
var concatenated = (String) X.class.getDeclaredMethod("concat", String.class, int.class)
        .invoke(new X(),"a", 1);

assertThat(concatenated, is("a1"));
```
**pay attention to parameters:**
```
@Test(expected = IllegalArgumentException.class)
public void invoke_wrongArguments() throws NoSuchMethodException,
        IllegalAccessException,
        InvocationTargetException {
    X.class.getDeclaredMethod("concat", String.class, int.class).invoke("a", "b");
}
```
**pay attention to accessibility:**
```
@Test(expected = IllegalAccessException.class)
public void invoke_private() throws NoSuchMethodException,
        IllegalAccessException,
        InvocationTargetException {
    X.class.getDeclaredMethod("privateMethod").invoke(new X());
}
```