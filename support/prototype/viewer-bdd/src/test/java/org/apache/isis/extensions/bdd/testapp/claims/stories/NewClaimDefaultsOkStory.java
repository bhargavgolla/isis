package org.apache.isis.extensions.bdd.testapp.claims.stories;

import org.apache.isis.extensions.bdd.testapp.claims.CustomCssPackage;
import org.apache.isis.viewer.bdd.concordion.AbstractIsisConcordionTest;


public class NewClaimDefaultsOkStory extends AbstractIsisConcordionTest {


	@Override
	protected Class<?> customCssPackage() {
		return CustomCssPackage.class;
	}
	
}