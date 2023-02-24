package com.example.designpatterninkotlinjava.structural

/**
 * Divide into 2 parts:
 * 1. Abstraction
 * 2. Implementation
 * **/

/**  https://www.geeksforgeeks.org/bridge-design-pattern/
 * 4 Elements to combine:
 * 1. Abstraction - core of the bridge design pattern and defines the crux. Contains a reference to the implementer
 * 2. Refined abstraction - Extends the abstraction takes the finer detail one level below. Hides the finer elements from implementers.
 * 3. Implementer – It defines the interface for implementation classes. This interface does not need to correspond directly to the abstraction interface and can be very different. Abstraction imp provides an implementation in terms of operations provided by the Implementer interface.
 * 4.Concrete Implementation – Implements the above implementer by providing the concrete implementation.
 * **/

