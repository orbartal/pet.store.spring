package pet.store.spring.dao.test.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pet.store.spring.dao.converters.interfaces.ConversionDaoServiceI;
import pet.store.spring.dao.repositories.PetsDaoRepositoryI;
import pet.store.spring.dao.test.config.TestAdoConfig;
import pet.store.spring.internal.model.classes.PetLogicEntityC;
import pet.store.spring.internal.model.interfaces.PetLogicEntityI;
import pet.store.spring.internal.services.interfaces.PetsDaoServiceI;

/*
 * https://www.luckyryan.com/2013/06/25/integration-testing-spring-data-jpa/ 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
@Import(TestAdoConfig.class)
public class petsDaoServiceIntegrationTestC {

	@Autowired
	protected PetsDaoRepositoryI m_petsDaoRepository;
	
	@Autowired
	protected ConversionDaoServiceI m_conversionDaoService;
	
	@Autowired
	protected PetsDaoServiceI m_petsDaoService;
	
	@Before
    public void setup() {
		Assert.assertNotNull(m_petsDaoRepository);
		Assert.assertNotNull(m_conversionDaoService);
		Assert.assertNotNull(m_petsDaoService);
    }

	@Test
    public void testCreateAndDeletePet () throws Exception {
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		m_petsDaoService.create(logicPet);
		PetLogicEntityI logicPet2 = m_petsDaoService.read(logicPet.getId());
		Assert.assertNotNull(logicPet2);
		Assert.assertEquals(logicPet, logicPet2);
		m_petsDaoService.delete(logicPet.getId());
		PetLogicEntityI logicPet3 = m_petsDaoService.read(logicPet.getId());
		Assert.assertNull(logicPet3);
	}
	
	@Test
    public void testReadPet () throws Exception {
		PetLogicEntityI logicPet = new PetLogicEntityC (1, "dog1", "free");
		PetLogicEntityI logicPet2 = m_petsDaoService.read(logicPet.getId());
		Assert.assertNull(logicPet2);
	}
}