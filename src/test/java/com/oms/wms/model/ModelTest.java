package com.oms.wms.model;

import com.oms.wms.persistence.model.*;
import com.oms.wms.persistence.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest// @ActiveProfiles("homolog")
class ModelTest {
    //UNIT TESTS(& COMPONENTS): VALIDATION, EXCEPTION
    //INTEGRATION TESTS: API INTEGRATION, REPOSITORY LAYER, API VALIDATION, DB INTEGRATION.

    Country country;
    State state;

    public GeometryFactory geometryFactory;
    public Coordinate[] coordinates;
    public CoordinateSequence coordinateSequence;
    public Point point;
    public LinearRing linearRing;

    @BeforeEach
    public void setUp() {
        geometryFactory = new GeometryFactory();
        coordinates = new Coordinate[]{new Coordinate(0, 0)};
        coordinateSequence = new CoordinateArraySequence(coordinates);
        point = new Point(coordinateSequence, geometryFactory);
        linearRing = geometryFactory.createLinearRing();

        country = new Country((int)(Math.random() * Math.random()), "Gadelha");
        state = new State("code", "Gadelha", country);
    }
    @Test
    public void RoleTest() {
        Collection<Privilege> privileges = new ArrayList<>();
        Role role = new Role("Gadelha", privileges);
        assertEquals("Gadelha", role.getName());
    }
    @Test
    public void StateTest() {
//        assertThrows();
//        assertNotNull(state);
//        assertNotEquals(state, state);
        assertEquals("Gadelha", state.getName());
    }
    @Test
    public void UserTest() {
        Collection<Role> roles = new ArrayList<>();
        User user = new User("Gadelha", "email", "password", true, roles);
        assertEquals("Gadelha", user.getUsername());
    }
    @Test
    public void UserDTOTest() {
        Collection<Role> roles = new ArrayList<>();
        User user = new User("Gadelha", "email", "password", true, roles);
        assertEquals("Gadelha", user.getUsername());
    }
}