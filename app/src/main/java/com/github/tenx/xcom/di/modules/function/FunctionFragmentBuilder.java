package com.github.tenx.xcom.di.modules.function;

import com.github.tenx.xcom.ui.Function.advertisements.AdvertisementsFragment;
import com.github.tenx.xcom.ui.Function.articles.ArticlesFragment;
import com.github.tenx.xcom.ui.Function.contactExperts.ContactExpertsFragment;
import com.github.tenx.xcom.ui.Function.questions.QuestionFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FunctionFragmentBuilder {

    @ContributesAndroidInjector(modules = {})
    abstract AdvertisementsFragment bindAdvertisementsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract ArticlesFragment bindArticlesFragment();

    @ContributesAndroidInjector(modules = {})
    abstract ContactExpertsFragment bindContactExpertsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract QuestionFragment bindQuestionsFragment();


}
