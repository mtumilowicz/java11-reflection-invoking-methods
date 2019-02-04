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