package io.syeony.premarket;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(DataConfig.class)
@EnableJpaAuditing
@ActiveProfiles(value = "test")
public abstract class JpaInfraTestSupport {

}
