package main.scala.com.rockthejvm

object ObjectOrientation extends App {
  // java equivalent: public static void main (String[] args)

  // scala is an object oriented language

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0
    // define methods
    def eat() = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String) extends Animal // constructor definition. name is a constructor argument
  val aDog = new Dog("Lassie")

  // constructor arguments are NOT fields: need to put a val before the constructor argument
  aDog.name

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true // by default public, can restrict by private or protected
    def walk(): Unit
  }

  // interface = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit // valid method name ?!
  }

  // single class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore with Philosopher { // can add many traits, but extends one class
    override def eat(animal: Animal): Unit = println("I am eating you, animal!")

    override def ?!(thought: String): Unit = println(s"I was thtinking: $thought")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object method argument, only available for methods with ONE argument
  // very expressive and similar to natural language

  aCroc ?! "What if we could fly?"

  // operators in Scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }
  /* what you tell the compiler

  class Carnivore_Anonymous_38499 extends Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can eat pretty much anything")
  }

  val dinosaur = new Carnivore_Anonymous_38499
   */

  // singleton object
  object MySingleton { // only instance of the MySingleton type
    val mySpecialValue = 39820
    def mySpecialMethod(): Int = 8429
    def apply(x: Int): Int = x + 1
  }
  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65) // equivalent to MySingleton.apply(65) - useful in functional programming

  object Animal { // companion - companion object
    // companions can access each other's private fields/methods
    // singleton animal and instances of animal are different things

    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods

  /*
  case classes = lightweight data structures with some boilerplate
  - sensible equals and hash code
  - serialization
  - companion with apply
  - pattern matching
   */
  case class Person(name: String, age: Int)

  // may be constructed without the keyword new
  val bob = Person("Bob", 54) // Person.apply("Bob", 54)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch { // catch (Exception e) {...}
    case e: Exception => "some faulty error message"
  } finally {
    // execute some code no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail
  val aStringList = List("hello", "Scala")
  val firsString = aStringList.head // string

  // point #1: in Scala we usually operate with IMMUTABLE values/objects
  // any modification to an object must return ANOTHER object
  /*
  Benefits:
  1. works miracles in multithreaded/distributed env
  2. helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // returns a NEW list

  // point #2: Scala is closest to the OO ideal
}
