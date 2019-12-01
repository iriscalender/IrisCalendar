package com.dsm.iriscalendar.presenter;

import com.dsm.iriscalendar.data.model.Category;
import com.dsm.iriscalendar.ui.category.CategoryContract;
import com.dsm.iriscalendar.ui.category.CategoryPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Flowable;
import retrofit2.Response;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryPresenterTests {

    @Mock
    private CategoryContract.View view;

    @Mock
    private CategoryContract.Repository repository;

    private CategoryContract.Presenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new CategoryPresenter(repository);
        presenter.createView(view);
    }

    @Test
    public void getCategorySuccessTest() {
        Category response = new Category();
        when(repository.getCategory())
                .thenReturn(Flowable.just(response));

        presenter.getCategory();

        verify(view).setCategory(response);
    }

    @Test
    public void modifyCategorySuccessTest() {
        Category response = new Category();
        when(repository.modifyCategory(view.getCategories()))
                .thenReturn(Flowable.just(Response.success(200, response)));

        presenter.modifyCategory();

        verify(view).toastModifySuccess();
    }
}
