

## Ejemplo Programático del Patrón Adapter en Java

El ejemplo del Patrón Adapter en Java muestra cómo una clase con una interfaz incompatible puede ser adaptada para funcionar con otra clase.

Considera un aspirante a capitán que solo puede usar botes de remos pero no puede navegar en absoluto.

Primero, tenemos las interfaces `RowingBoat` y `FishingBoat`

```java
public interface RowingBoat {
    void row();
}

@Slf4j
public class FishingBoat {
    public void sail() {
        LOGGER.info("The fishing boat is sailing");
    }
}
```

El capitán espera una implementación de la interfaz `RowingBoat` para poder moverse.

```java
public class Captain {

    private final RowingBoat rowingBoat;

    // constructor por defecto y setter para rowingBoat
    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void row() {
        rowingBoat.row();
    }
}
```

Ahora, digamos que los piratas están llegando y nuestro capitán necesita escapar, pero solo hay un bote de pesca disponible. Necesitamos crear un adaptador que le permita al capitán operar el bote de pesca con sus habilidades de remo.

```java
@Slf4j
public class FishingBoatAdapter implements RowingBoat {

    private final FishingBoat boat;

    public FishingBoatAdapter() {
        boat = new FishingBoat();
    }

    @Override
    public void row() {
        boat.sail();
    }
}
```

Ahora el `Captain` puede usar el `FishingBoat` para escapar de los piratas.

```java
  public static void main(final String[] args) {
    // El capitán solo puede operar botes de remos, pero con el adaptador
    // también puede usar botes de pesca
    var captain = new Captain(new FishingBoatAdapter());
    captain.row();
}
```

La salida del programa es:

```
10:25:08.074 [main] INFO com.iluwatar.adapter.FishingBoat -- The fishing boat is sailing
```

---


## Ejemplo Programático del Patrón Bridge en Java

El ejemplo del Patrón Bridge en Java muestra cómo separar el tipo de barco (abstracción) del sistema de propulsión (implementación).

Primero, tenemos la interfaz `PropulsionSystem` (Implementor) y sus implementaciones concretas:

```java
public interface PropulsionSystem {
    void propel();
}

@Slf4j
public class SailPropulsion implements PropulsionSystem {
    @Override
    public void propel() {
        LOGGER.info("The boat is being propelled by sails");
    }
}

@Slf4j
public class MotorPropulsion implements PropulsionSystem {
    @Override
    public void propel() {
        LOGGER.info("The boat is being propelled by motor");
    }
}
```

Luego, la clase abstracta `Boat` (Abstraction) que mantiene una referencia al `PropulsionSystem`:

```java
public abstract class Boat {

    protected final PropulsionSystem propulsionSystem;

    protected Boat(PropulsionSystem propulsionSystem) {
        this.propulsionSystem = propulsionSystem;
    }

    public abstract void move();

    protected void propel() {
        propulsionSystem.propel();
    }
}
```

Las abstracciones refinadas `PassengerBoat` y `CargoBoat` extienden la abstracción:

```java
@Slf4j
public class PassengerBoat extends Boat {

    public PassengerBoat(PropulsionSystem propulsionSystem) {
        super(propulsionSystem);
    }

    @Override
    public void move() {
        LOGGER.info("The passenger boat is moving");
        propel();
    }
}

@Slf4j
public class CargoBoat extends Boat {

    public CargoBoat(PropulsionSystem propulsionSystem) {
        super(propulsionSystem);
    }

    @Override
    public void move() {
        LOGGER.info("The cargo boat is moving");
        propel();
    }
}
```

Ahora podemos combinar cualquier tipo de barco con cualquier sistema de propulsión:

```java
  public static void main(final String[] args) {
    // Un barco de pasajeros con propulsión a vela
    Boat passengerBoat = new PassengerBoat(new SailPropulsion());
    passengerBoat.move();

    // Un barco de carga con propulsión a motor
    Boat cargoBoat = new CargoBoat(new MotorPropulsion());
    cargoBoat.move();
}
```

La salida del programa es:

```
10:25:08.080 [main] INFO com.iluwatar.adapter.bridge.PassengerBoat -- The passenger boat is moving
10:25:08.081 [main] INFO com.iluwatar.adapter.bridge.SailPropulsion -- The boat is being propelled by sails
10:25:08.082 [main] INFO com.iluwatar.adapter.bridge.CargoBoat -- The cargo boat is moving
10:25:08.083 [main] INFO com.iluwatar.adapter.bridge.MotorPropulsion -- The boat is being propelled by motor
```
