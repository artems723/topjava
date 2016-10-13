package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

/**
 * Created by AStaver on 07.10.2016.
 */
@ActiveProfiles(Profiles.JDBC)
public class UserServiceJdbcTest extends AbstractUserServiceTest {
}
