package org.apache.isis.examples.webshop.objstore.dflt.catalog.categories;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.examples.webshop.dom.catalog.categories.ProductCategories;
import org.apache.isis.examples.webshop.dom.catalog.categories.ProductCategory;


public class ProductCategoriesDefault extends AbstractFactoryAndRepository implements ProductCategories {

    // {{ Id, iconName
    @Override
    public String getId() {
        return "categories";
    }

	public String iconName() {
		return "ProductCategory";
	}
	// }}

    @Override
    public ProductCategory findByCode(final String code) {
        return firstMatch(ProductCategory.class, new Filter<ProductCategory>(){

            @Override
            public boolean accept(ProductCategory t) {
                return t.hasCode(code);
            }});
    }
	

}