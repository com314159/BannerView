package com.banner;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bannerview.R;


public class BannerView extends LinearLayout{
	
	private Context mContext;
	private InfiniteViewPager mViewPager;
	private double mHeight;
	private double mItemHeight;
	private double mItemWidth=300;
	private double mMargin;
	private int mShowPictureSize=4;//要显示多少张图片一屏
	
	public BannerView(Context context) {
		super(context);
		initView(context);
	}

	public BannerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public BannerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		mContext = context;
		
		View view = LayoutInflater.from(mContext).inflate(R.layout.bannerview,this,true);
		
		mViewPager = (InfiniteViewPager)view.findViewById(R.id.bannerview_viewpager);
		
		caculateLaoutParameter();
		android.view.ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
		lp.height = (int) mHeight;
		mViewPager.setLayoutParams(lp);
		
		
		ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(mContext,(int)mItemWidth,(int)mItemHeight);
		mViewPager.setAdapter(new InfinitePagerAdapter(imagePagerAdapter));
		mViewPager.setPageMargin((int) mMargin);
		mViewPager.setOffscreenPageLimit(mShowPictureSize+2);
	}
	
	private void caculateLaoutParameter(){
		mHeight = ScreenParamUtil.GetScreenHeightPx(mContext)*0.25;
		mItemHeight = mHeight*13/16.0;
		float itemMargin = mContext.getResources().getDimension(R.dimen.bannerview_item_margin);
		float viewPagerMargin = mContext.getResources().getDimension(R.dimen.bannerview_pagerView_margin);
		float viewPagerWidth = ScreenParamUtil.GetScreenWidthPx(mContext)-2*viewPagerMargin;
		mItemWidth = (viewPagerWidth-(mShowPictureSize-1)*itemMargin)/(float)mShowPictureSize;
		mMargin = (viewPagerWidth-mItemWidth-itemMargin)*-1;
	}
	
	private class ImagePagerAdapter extends PagerAdapter {

		private Context mContext;
		private int mHeight;
		private int mWidth;
		
		int[] mImages = {
				R.drawable.banner1,
				R.drawable.banner2,
				R.drawable.banner3,
				R.drawable.banner4,
				R.drawable.banner5,
				R.drawable.banner6,
				R.drawable.banner7,
		};
		
		ImagePagerAdapter(Context context,int width,int height){
			super();
			mContext = context;
			mWidth = width;
			mHeight = height;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			return mImages.length;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = LayoutInflater.from(mContext).inflate(R.layout.bannerview_item, view, false);
			ImageView imageView = (ImageView) imageLayout.findViewById(R.id.bi_imageView);
			
			android.view.ViewGroup.LayoutParams lp = imageView.getLayoutParams();
			lp.height = mHeight;
			lp.width = mWidth;
			imageView.setLayoutParams(lp);
			
			imageView.setBackgroundResource(mImages[position]);
			
		    view.addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
		
		
	}
}
