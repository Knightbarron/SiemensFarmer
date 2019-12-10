package com.github.tenx.xcom.di.modules.function;

import com.github.tenx.xcom.ui.Function.shop.ShopFragment;
import com.github.tenx.xcom.ui.Function.articles.ArticlesFragment;
import com.github.tenx.xcom.ui.Function.contactExperts.ContactExpertsFragment;
import com.github.tenx.xcom.ui.Function.prediction.PredictionFragment;
import com.github.tenx.xcom.ui.Function.questions.QuestionFragment;

import com.github.tenx.xcom.ui.Function.singleItemShop.SingleItemShopFragment;
import com.github.tenx.xcom.ui.Function.singlearticle.SingleArticleFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FunctionFragmentBuilder {

    @ContributesAndroidInjector(modules = {})
    abstract ShopFragment bindAdvertisementsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract ArticlesFragment bindArticlesFragment();

    @ContributesAndroidInjector(modules = {})
    abstract ContactExpertsFragment bindContactExpertsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract QuestionFragment bindQuestionsFragment();

    @ContributesAndroidInjector(modules = {})
    abstract PredictionFragment bindPredictionFragment();

    @ContributesAndroidInjector
    abstract SingleArticleFragment bindSingleArticleFragment();

    @ContributesAndroidInjector
    abstract SingleItemShopFragment bindSingleItemShopFragment();



}
