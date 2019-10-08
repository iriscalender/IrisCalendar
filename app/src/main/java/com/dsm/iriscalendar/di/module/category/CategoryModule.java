package com.dsm.iriscalendar.di.module.category;

import com.dsm.iriscalendar.data.Api;
import com.dsm.iriscalendar.di.scope.CategoryActivityScope;
import com.dsm.iriscalendar.ui.category.CategoryContract;
import com.dsm.iriscalendar.ui.category.CategoryPresenter;
import com.dsm.iriscalendar.ui.category.CategoryRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryModule {

    @CategoryActivityScope
    @Provides
    CategoryContract.Repository provideCategoryRepository(Api api) {
        return new CategoryRepository(api);
    }

    @CategoryActivityScope
    @Provides
    CategoryContract.Presenter provideCategoryPresenter(CategoryContract.Repository repository) {
        return new CategoryPresenter(repository);
    }
}
