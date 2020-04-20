package ru.ailp.abstr;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ailp.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
@ActiveProfiles("test")
public abstract class AbstractTest {

    @Autowired
    @NonNull WebApplicationContext wac;

    @Autowired
    @NonNull
    public MockMvc mockMvcWithOutAuthorization;

    @Autowired
    @NonNull
    public MockMvc mockMvcWithAuthorization;

    @Autowired
    @NonNull UserService userService;

    @BeforeEach
    void setUp() {
        this.mockMvcWithAuthorization = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    protected void setAuthAdminContext() {
        UserDetails user = userService.loadUserByUsername("admin");
        Authentication auth = new UsernamePasswordAuthenticationToken(user, "test");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
