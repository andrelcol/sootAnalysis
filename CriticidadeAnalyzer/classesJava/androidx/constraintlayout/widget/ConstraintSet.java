package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet {
  private static final int[] VISIBILITY_FLAGS = new int[] { 0, 4, 8 };
  
  private static SparseIntArray mapToConstant = new SparseIntArray();
  
  private HashMap<Integer, Constraint> mConstraints = new HashMap<Integer, Constraint>();
  
  static {
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
    mapToConstant.append(R$styleable.ConstraintSet_layout_editor_absoluteX, 6);
    mapToConstant.append(R$styleable.ConstraintSet_layout_editor_absoluteY, 7);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintGuide_begin, 17);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintGuide_end, 18);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintGuide_percent, 19);
    mapToConstant.append(R$styleable.ConstraintSet_android_orientation, 27);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
    mapToConstant.append(R$styleable.ConstraintSet_layout_goneMarginLeft, 13);
    mapToConstant.append(R$styleable.ConstraintSet_layout_goneMarginTop, 16);
    mapToConstant.append(R$styleable.ConstraintSet_layout_goneMarginRight, 14);
    mapToConstant.append(R$styleable.ConstraintSet_layout_goneMarginBottom, 11);
    mapToConstant.append(R$styleable.ConstraintSet_layout_goneMarginStart, 15);
    mapToConstant.append(R$styleable.ConstraintSet_layout_goneMarginEnd, 12);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintVertical_weight, 40);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintVertical_bias, 37);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintLeft_creator, 75);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintTop_creator, 75);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintRight_creator, 75);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintBottom_creator, 75);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintBaseline_creator, 75);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_marginLeft, 24);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_marginRight, 28);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_marginStart, 31);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_marginEnd, 8);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_marginTop, 34);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_marginBottom, 2);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_width, 23);
    mapToConstant.append(R$styleable.ConstraintSet_android_layout_height, 21);
    mapToConstant.append(R$styleable.ConstraintSet_android_visibility, 22);
    mapToConstant.append(R$styleable.ConstraintSet_android_alpha, 43);
    mapToConstant.append(R$styleable.ConstraintSet_android_elevation, 44);
    mapToConstant.append(R$styleable.ConstraintSet_android_rotationX, 45);
    mapToConstant.append(R$styleable.ConstraintSet_android_rotationY, 46);
    mapToConstant.append(R$styleable.ConstraintSet_android_rotation, 60);
    mapToConstant.append(R$styleable.ConstraintSet_android_scaleX, 47);
    mapToConstant.append(R$styleable.ConstraintSet_android_scaleY, 48);
    mapToConstant.append(R$styleable.ConstraintSet_android_transformPivotX, 49);
    mapToConstant.append(R$styleable.ConstraintSet_android_transformPivotY, 50);
    mapToConstant.append(R$styleable.ConstraintSet_android_translationX, 51);
    mapToConstant.append(R$styleable.ConstraintSet_android_translationY, 52);
    mapToConstant.append(R$styleable.ConstraintSet_android_translationZ, 53);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintWidth_default, 54);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHeight_default, 55);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintWidth_max, 56);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHeight_max, 57);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintWidth_min, 58);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHeight_min, 59);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintCircle, 61);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintCircleRadius, 62);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintCircleAngle, 63);
    mapToConstant.append(R$styleable.ConstraintSet_android_id, 38);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintWidth_percent, 69);
    mapToConstant.append(R$styleable.ConstraintSet_layout_constraintHeight_percent, 70);
    mapToConstant.append(R$styleable.ConstraintSet_chainUseRtl, 71);
    mapToConstant.append(R$styleable.ConstraintSet_barrierDirection, 72);
    mapToConstant.append(R$styleable.ConstraintSet_constraint_referenced_ids, 73);
    mapToConstant.append(R$styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
  }
  
  private int[] convertReferenceString(View paramView, String paramString) {
    String[] arrayOfString = paramString.split(",");
    Context context = paramView.getContext();
    int[] arrayOfInt2 = new int[arrayOfString.length];
    byte b2 = 0;
    byte b1;
    for (b1 = 0; b2 < arrayOfString.length; b1++) {
      String str = arrayOfString[b2].trim();
      try {
        j = R$id.class.getField(str).getInt(null);
      } catch (Exception exception) {
        j = 0;
      } 
      int i = j;
      if (!j)
        i = context.getResources().getIdentifier(str, "id", context.getPackageName()); 
      int j = i;
      if (i == 0) {
        j = i;
        if (paramView.isInEditMode()) {
          j = i;
          if (paramView.getParent() instanceof ConstraintLayout) {
            Object object = ((ConstraintLayout)paramView.getParent()).getDesignInformation(0, str);
            j = i;
            if (object != null) {
              j = i;
              if (object instanceof Integer)
                j = ((Integer)object).intValue(); 
            } 
          } 
        } 
      } 
      arrayOfInt2[b1] = j;
      b2++;
    } 
    int[] arrayOfInt1 = arrayOfInt2;
    if (b1 != arrayOfString.length)
      arrayOfInt1 = Arrays.copyOf(arrayOfInt2, b1); 
    return arrayOfInt1;
  }
  
  private Constraint fillFromAttributeList(Context paramContext, AttributeSet paramAttributeSet) {
    Constraint constraint = new Constraint();
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R$styleable.ConstraintSet);
    populateConstraint(constraint, typedArray);
    typedArray.recycle();
    return constraint;
  }
  
  private static int lookupID(TypedArray paramTypedArray, int paramInt1, int paramInt2) {
    int i = paramTypedArray.getResourceId(paramInt1, paramInt2);
    paramInt2 = i;
    if (i == -1)
      paramInt2 = paramTypedArray.getInt(paramInt1, -1); 
    return paramInt2;
  }
  
  private void populateConstraint(Constraint paramConstraint, TypedArray paramTypedArray) {
    int i = paramTypedArray.getIndexCount();
    for (byte b = 0; b < i; b++) {
      StringBuilder stringBuilder;
      int k = paramTypedArray.getIndex(b);
      int j = mapToConstant.get(k);
      switch (j) {
        default:
          switch (j) {
            default:
              switch (j) {
                default:
                  stringBuilder = new StringBuilder();
                  stringBuilder.append("Unknown attribute 0x");
                  stringBuilder.append(Integer.toHexString(k));
                  stringBuilder.append("   ");
                  stringBuilder.append(mapToConstant.get(k));
                  stringBuilder.toString();
                  break;
                case 75:
                  stringBuilder = new StringBuilder();
                  stringBuilder.append("unused attribute 0x");
                  stringBuilder.append(Integer.toHexString(k));
                  stringBuilder.append("   ");
                  stringBuilder.append(mapToConstant.get(k));
                  stringBuilder.toString();
                  break;
                case 74:
                  paramConstraint.mBarrierAllowsGoneWidgets = paramTypedArray.getBoolean(k, paramConstraint.mBarrierAllowsGoneWidgets);
                  break;
                case 73:
                  paramConstraint.mReferenceIdString = paramTypedArray.getString(k);
                  break;
                case 72:
                  paramConstraint.mBarrierDirection = paramTypedArray.getInt(k, paramConstraint.mBarrierDirection);
                  break;
                case 70:
                  paramConstraint.heightPercent = paramTypedArray.getFloat(k, 1.0F);
                  break;
                case 69:
                  paramConstraint.widthPercent = paramTypedArray.getFloat(k, 1.0F);
                  break;
                case 71:
                  break;
              } 
              break;
            case 63:
              paramConstraint.circleAngle = paramTypedArray.getFloat(k, paramConstraint.circleAngle);
              break;
            case 62:
              paramConstraint.circleRadius = paramTypedArray.getDimensionPixelSize(k, paramConstraint.circleRadius);
              break;
            case 61:
              paramConstraint.circleConstraint = lookupID(paramTypedArray, k, paramConstraint.circleConstraint);
              break;
            case 60:
              break;
          } 
          paramConstraint.rotation = paramTypedArray.getFloat(k, paramConstraint.rotation);
          break;
        case 53:
          paramConstraint.translationZ = paramTypedArray.getDimension(k, paramConstraint.translationZ);
          break;
        case 52:
          paramConstraint.translationY = paramTypedArray.getDimension(k, paramConstraint.translationY);
          break;
        case 51:
          paramConstraint.translationX = paramTypedArray.getDimension(k, paramConstraint.translationX);
          break;
        case 50:
          paramConstraint.transformPivotY = paramTypedArray.getFloat(k, paramConstraint.transformPivotY);
          break;
        case 49:
          paramConstraint.transformPivotX = paramTypedArray.getFloat(k, paramConstraint.transformPivotX);
          break;
        case 48:
          paramConstraint.scaleY = paramTypedArray.getFloat(k, paramConstraint.scaleY);
          break;
        case 47:
          paramConstraint.scaleX = paramTypedArray.getFloat(k, paramConstraint.scaleX);
          break;
        case 46:
          paramConstraint.rotationY = paramTypedArray.getFloat(k, paramConstraint.rotationY);
          break;
        case 45:
          paramConstraint.rotationX = paramTypedArray.getFloat(k, paramConstraint.rotationX);
          break;
        case 44:
          paramConstraint.applyElevation = true;
          paramConstraint.elevation = paramTypedArray.getDimension(k, paramConstraint.elevation);
          break;
        case 43:
          paramConstraint.alpha = paramTypedArray.getFloat(k, paramConstraint.alpha);
          break;
        case 42:
          paramConstraint.verticalChainStyle = paramTypedArray.getInt(k, paramConstraint.verticalChainStyle);
          break;
        case 41:
          paramConstraint.horizontalChainStyle = paramTypedArray.getInt(k, paramConstraint.horizontalChainStyle);
          break;
        case 40:
          paramConstraint.verticalWeight = paramTypedArray.getFloat(k, paramConstraint.verticalWeight);
          break;
        case 39:
          paramConstraint.horizontalWeight = paramTypedArray.getFloat(k, paramConstraint.horizontalWeight);
          break;
        case 38:
          paramConstraint.mViewId = paramTypedArray.getResourceId(k, paramConstraint.mViewId);
          break;
        case 37:
          paramConstraint.verticalBias = paramTypedArray.getFloat(k, paramConstraint.verticalBias);
          break;
        case 36:
          paramConstraint.topToTop = lookupID(paramTypedArray, k, paramConstraint.topToTop);
          break;
        case 35:
          paramConstraint.topToBottom = lookupID(paramTypedArray, k, paramConstraint.topToBottom);
          break;
        case 34:
          paramConstraint.topMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.topMargin);
          break;
        case 33:
          paramConstraint.startToStart = lookupID(paramTypedArray, k, paramConstraint.startToStart);
          break;
        case 32:
          paramConstraint.startToEnd = lookupID(paramTypedArray, k, paramConstraint.startToEnd);
          break;
        case 31:
          paramConstraint.startMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.startMargin);
          break;
        case 30:
          paramConstraint.rightToRight = lookupID(paramTypedArray, k, paramConstraint.rightToRight);
          break;
        case 29:
          paramConstraint.rightToLeft = lookupID(paramTypedArray, k, paramConstraint.rightToLeft);
          break;
        case 28:
          paramConstraint.rightMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.rightMargin);
          break;
        case 27:
          paramConstraint.orientation = paramTypedArray.getInt(k, paramConstraint.orientation);
          break;
        case 26:
          paramConstraint.leftToRight = lookupID(paramTypedArray, k, paramConstraint.leftToRight);
          break;
        case 25:
          paramConstraint.leftToLeft = lookupID(paramTypedArray, k, paramConstraint.leftToLeft);
          break;
        case 24:
          paramConstraint.leftMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.leftMargin);
          break;
        case 23:
          paramConstraint.mWidth = paramTypedArray.getLayoutDimension(k, paramConstraint.mWidth);
          break;
        case 22:
          paramConstraint.visibility = paramTypedArray.getInt(k, paramConstraint.visibility);
          paramConstraint.visibility = VISIBILITY_FLAGS[paramConstraint.visibility];
          break;
        case 21:
          paramConstraint.mHeight = paramTypedArray.getLayoutDimension(k, paramConstraint.mHeight);
          break;
        case 20:
          paramConstraint.horizontalBias = paramTypedArray.getFloat(k, paramConstraint.horizontalBias);
          break;
        case 19:
          paramConstraint.guidePercent = paramTypedArray.getFloat(k, paramConstraint.guidePercent);
          break;
        case 18:
          paramConstraint.guideEnd = paramTypedArray.getDimensionPixelOffset(k, paramConstraint.guideEnd);
          break;
        case 17:
          paramConstraint.guideBegin = paramTypedArray.getDimensionPixelOffset(k, paramConstraint.guideBegin);
          break;
        case 16:
          paramConstraint.goneTopMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.goneTopMargin);
          break;
        case 15:
          paramConstraint.goneStartMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.goneStartMargin);
          break;
        case 14:
          paramConstraint.goneRightMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.goneRightMargin);
          break;
        case 13:
          paramConstraint.goneLeftMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.goneLeftMargin);
          break;
        case 12:
          paramConstraint.goneEndMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.goneEndMargin);
          break;
        case 11:
          paramConstraint.goneBottomMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.goneBottomMargin);
          break;
        case 10:
          paramConstraint.endToStart = lookupID(paramTypedArray, k, paramConstraint.endToStart);
          break;
        case 9:
          paramConstraint.endToEnd = lookupID(paramTypedArray, k, paramConstraint.endToEnd);
          break;
        case 8:
          paramConstraint.endMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.endMargin);
          break;
        case 7:
          paramConstraint.editorAbsoluteY = paramTypedArray.getDimensionPixelOffset(k, paramConstraint.editorAbsoluteY);
          break;
        case 6:
          paramConstraint.editorAbsoluteX = paramTypedArray.getDimensionPixelOffset(k, paramConstraint.editorAbsoluteX);
          break;
        case 5:
          paramConstraint.dimensionRatio = paramTypedArray.getString(k);
          break;
        case 4:
          paramConstraint.bottomToTop = lookupID(paramTypedArray, k, paramConstraint.bottomToTop);
          break;
        case 3:
          paramConstraint.bottomToBottom = lookupID(paramTypedArray, k, paramConstraint.bottomToBottom);
          break;
        case 2:
          paramConstraint.bottomMargin = paramTypedArray.getDimensionPixelSize(k, paramConstraint.bottomMargin);
          break;
        case 1:
          paramConstraint.baselineToBaseline = lookupID(paramTypedArray, k, paramConstraint.baselineToBaseline);
          break;
      } 
    } 
  }
  
  void applyToInternal(ConstraintLayout paramConstraintLayout) {
    int j = paramConstraintLayout.getChildCount();
    HashSet hashSet = new HashSet(this.mConstraints.keySet());
    int i = 0;
    while (i < j) {
      View view = paramConstraintLayout.getChildAt(i);
      int k = view.getId();
      if (k != -1) {
        if (this.mConstraints.containsKey(Integer.valueOf(k))) {
          hashSet.remove(Integer.valueOf(k));
          Constraint constraint = this.mConstraints.get(Integer.valueOf(k));
          if (view instanceof Barrier)
            constraint.mHelperType = 1; 
          int m = constraint.mHelperType;
          if (m != -1 && m == 1) {
            Barrier barrier = (Barrier)view;
            barrier.setId(k);
            barrier.setType(constraint.mBarrierDirection);
            barrier.setAllowsGoneWidget(constraint.mBarrierAllowsGoneWidgets);
            int[] arrayOfInt = constraint.mReferenceIds;
            if (arrayOfInt != null) {
              barrier.setReferencedIds(arrayOfInt);
            } else {
              String str = constraint.mReferenceIdString;
              if (str != null) {
                constraint.mReferenceIds = convertReferenceString(barrier, str);
                barrier.setReferencedIds(constraint.mReferenceIds);
              } 
            } 
          } 
          ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)view.getLayoutParams();
          constraint.applyTo(layoutParams);
          view.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
          view.setVisibility(constraint.visibility);
          if (Build.VERSION.SDK_INT >= 17) {
            view.setAlpha(constraint.alpha);
            view.setRotation(constraint.rotation);
            view.setRotationX(constraint.rotationX);
            view.setRotationY(constraint.rotationY);
            view.setScaleX(constraint.scaleX);
            view.setScaleY(constraint.scaleY);
            if (!Float.isNaN(constraint.transformPivotX))
              view.setPivotX(constraint.transformPivotX); 
            if (!Float.isNaN(constraint.transformPivotY))
              view.setPivotY(constraint.transformPivotY); 
            view.setTranslationX(constraint.translationX);
            view.setTranslationY(constraint.translationY);
            if (Build.VERSION.SDK_INT >= 21) {
              view.setTranslationZ(constraint.translationZ);
              if (constraint.applyElevation)
                view.setElevation(constraint.elevation); 
            } 
          } 
        } 
        i++;
        continue;
      } 
      throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
    } 
    for (Integer integer : hashSet) {
      Constraint constraint = this.mConstraints.get(integer);
      i = constraint.mHelperType;
      if (i != -1 && i == 1) {
        Barrier barrier = new Barrier(paramConstraintLayout.getContext());
        barrier.setId(integer.intValue());
        int[] arrayOfInt = constraint.mReferenceIds;
        if (arrayOfInt != null) {
          barrier.setReferencedIds(arrayOfInt);
        } else {
          String str = constraint.mReferenceIdString;
          if (str != null) {
            constraint.mReferenceIds = convertReferenceString(barrier, str);
            barrier.setReferencedIds(constraint.mReferenceIds);
          } 
        } 
        barrier.setType(constraint.mBarrierDirection);
        ConstraintLayout.LayoutParams layoutParams = paramConstraintLayout.generateDefaultLayoutParams();
        barrier.validateParams();
        constraint.applyTo(layoutParams);
        paramConstraintLayout.addView(barrier, (ViewGroup.LayoutParams)layoutParams);
      } 
      if (constraint.mIsGuideline) {
        Guideline guideline = new Guideline(paramConstraintLayout.getContext());
        guideline.setId(integer.intValue());
        ConstraintLayout.LayoutParams layoutParams = paramConstraintLayout.generateDefaultLayoutParams();
        constraint.applyTo(layoutParams);
        paramConstraintLayout.addView(guideline, (ViewGroup.LayoutParams)layoutParams);
      } 
    } 
  }
  
  public void clone(Constraints paramConstraints) {
    int i = paramConstraints.getChildCount();
    this.mConstraints.clear();
    byte b = 0;
    while (b < i) {
      View view = paramConstraints.getChildAt(b);
      Constraints.LayoutParams layoutParams = (Constraints.LayoutParams)view.getLayoutParams();
      int j = view.getId();
      if (j != -1) {
        if (!this.mConstraints.containsKey(Integer.valueOf(j)))
          this.mConstraints.put(Integer.valueOf(j), new Constraint()); 
        Constraint constraint = this.mConstraints.get(Integer.valueOf(j));
        if (view instanceof ConstraintHelper)
          constraint.fillFromConstraints((ConstraintHelper)view, j, layoutParams); 
        constraint.fillFromConstraints(j, layoutParams);
        b++;
        continue;
      } 
      throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
    } 
  }
  
  public void load(Context paramContext, int paramInt) {
    XmlResourceParser xmlResourceParser = paramContext.getResources().getXml(paramInt);
    try {
      for (paramInt = xmlResourceParser.getEventType(); paramInt != 1; paramInt = xmlResourceParser.next()) {
        if (paramInt != 0) {
          if (paramInt == 2) {
            String str = xmlResourceParser.getName();
            Constraint constraint = fillFromAttributeList(paramContext, Xml.asAttributeSet((XmlPullParser)xmlResourceParser));
            if (str.equalsIgnoreCase("Guideline"))
              constraint.mIsGuideline = true; 
            this.mConstraints.put(Integer.valueOf(constraint.mViewId), constraint);
          } 
        } else {
          xmlResourceParser.getName();
        } 
      } 
    } catch (XmlPullParserException xmlPullParserException) {
      xmlPullParserException.printStackTrace();
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
  }
  
  private static class Constraint {
    public float alpha = 1.0F;
    
    public boolean applyElevation = false;
    
    public int baselineToBaseline = -1;
    
    public int bottomMargin = -1;
    
    public int bottomToBottom = -1;
    
    public int bottomToTop = -1;
    
    public float circleAngle = 0.0F;
    
    public int circleConstraint = -1;
    
    public int circleRadius = 0;
    
    public boolean constrainedHeight = false;
    
    public boolean constrainedWidth = false;
    
    public String dimensionRatio = null;
    
    public int editorAbsoluteX = -1;
    
    public int editorAbsoluteY = -1;
    
    public float elevation = 0.0F;
    
    public int endMargin = -1;
    
    public int endToEnd = -1;
    
    public int endToStart = -1;
    
    public int goneBottomMargin = -1;
    
    public int goneEndMargin = -1;
    
    public int goneLeftMargin = -1;
    
    public int goneRightMargin = -1;
    
    public int goneStartMargin = -1;
    
    public int goneTopMargin = -1;
    
    public int guideBegin = -1;
    
    public int guideEnd = -1;
    
    public float guidePercent = -1.0F;
    
    public int heightDefault = 0;
    
    public int heightMax = -1;
    
    public int heightMin = -1;
    
    public float heightPercent = 1.0F;
    
    public float horizontalBias = 0.5F;
    
    public int horizontalChainStyle = 0;
    
    public float horizontalWeight = 0.0F;
    
    public int leftMargin = -1;
    
    public int leftToLeft = -1;
    
    public int leftToRight = -1;
    
    public boolean mBarrierAllowsGoneWidgets = false;
    
    public int mBarrierDirection = -1;
    
    public int mHeight;
    
    public int mHelperType = -1;
    
    boolean mIsGuideline = false;
    
    public String mReferenceIdString;
    
    public int[] mReferenceIds;
    
    int mViewId;
    
    public int mWidth;
    
    public int orientation = -1;
    
    public int rightMargin = -1;
    
    public int rightToLeft = -1;
    
    public int rightToRight = -1;
    
    public float rotation = 0.0F;
    
    public float rotationX = 0.0F;
    
    public float rotationY = 0.0F;
    
    public float scaleX = 1.0F;
    
    public float scaleY = 1.0F;
    
    public int startMargin = -1;
    
    public int startToEnd = -1;
    
    public int startToStart = -1;
    
    public int topMargin = -1;
    
    public int topToBottom = -1;
    
    public int topToTop = -1;
    
    public float transformPivotX = Float.NaN;
    
    public float transformPivotY = Float.NaN;
    
    public float translationX = 0.0F;
    
    public float translationY = 0.0F;
    
    public float translationZ = 0.0F;
    
    public float verticalBias = 0.5F;
    
    public int verticalChainStyle = 0;
    
    public float verticalWeight = 0.0F;
    
    public int visibility = 0;
    
    public int widthDefault = 0;
    
    public int widthMax = -1;
    
    public int widthMin = -1;
    
    public float widthPercent = 1.0F;
    
    private Constraint() {}
    
    private void fillFrom(int param1Int, ConstraintLayout.LayoutParams param1LayoutParams) {
      this.mViewId = param1Int;
      this.leftToLeft = param1LayoutParams.leftToLeft;
      this.leftToRight = param1LayoutParams.leftToRight;
      this.rightToLeft = param1LayoutParams.rightToLeft;
      this.rightToRight = param1LayoutParams.rightToRight;
      this.topToTop = param1LayoutParams.topToTop;
      this.topToBottom = param1LayoutParams.topToBottom;
      this.bottomToTop = param1LayoutParams.bottomToTop;
      this.bottomToBottom = param1LayoutParams.bottomToBottom;
      this.baselineToBaseline = param1LayoutParams.baselineToBaseline;
      this.startToEnd = param1LayoutParams.startToEnd;
      this.startToStart = param1LayoutParams.startToStart;
      this.endToStart = param1LayoutParams.endToStart;
      this.endToEnd = param1LayoutParams.endToEnd;
      this.horizontalBias = param1LayoutParams.horizontalBias;
      this.verticalBias = param1LayoutParams.verticalBias;
      this.dimensionRatio = param1LayoutParams.dimensionRatio;
      this.circleConstraint = param1LayoutParams.circleConstraint;
      this.circleRadius = param1LayoutParams.circleRadius;
      this.circleAngle = param1LayoutParams.circleAngle;
      this.editorAbsoluteX = param1LayoutParams.editorAbsoluteX;
      this.editorAbsoluteY = param1LayoutParams.editorAbsoluteY;
      this.orientation = param1LayoutParams.orientation;
      this.guidePercent = param1LayoutParams.guidePercent;
      this.guideBegin = param1LayoutParams.guideBegin;
      this.guideEnd = param1LayoutParams.guideEnd;
      this.mWidth = param1LayoutParams.width;
      this.mHeight = param1LayoutParams.height;
      this.leftMargin = param1LayoutParams.leftMargin;
      this.rightMargin = param1LayoutParams.rightMargin;
      this.topMargin = param1LayoutParams.topMargin;
      this.bottomMargin = param1LayoutParams.bottomMargin;
      this.verticalWeight = param1LayoutParams.verticalWeight;
      this.horizontalWeight = param1LayoutParams.horizontalWeight;
      this.verticalChainStyle = param1LayoutParams.verticalChainStyle;
      this.horizontalChainStyle = param1LayoutParams.horizontalChainStyle;
      boolean bool = param1LayoutParams.constrainedWidth;
      this.constrainedWidth = bool;
      this.constrainedHeight = param1LayoutParams.constrainedHeight;
      this.widthDefault = param1LayoutParams.matchConstraintDefaultWidth;
      this.heightDefault = param1LayoutParams.matchConstraintDefaultHeight;
      this.constrainedWidth = bool;
      this.widthMax = param1LayoutParams.matchConstraintMaxWidth;
      this.heightMax = param1LayoutParams.matchConstraintMaxHeight;
      this.widthMin = param1LayoutParams.matchConstraintMinWidth;
      this.heightMin = param1LayoutParams.matchConstraintMinHeight;
      this.widthPercent = param1LayoutParams.matchConstraintPercentWidth;
      this.heightPercent = param1LayoutParams.matchConstraintPercentHeight;
      if (Build.VERSION.SDK_INT >= 17) {
        this.endMargin = param1LayoutParams.getMarginEnd();
        this.startMargin = param1LayoutParams.getMarginStart();
      } 
    }
    
    private void fillFromConstraints(int param1Int, Constraints.LayoutParams param1LayoutParams) {
      fillFrom(param1Int, param1LayoutParams);
      this.alpha = param1LayoutParams.alpha;
      this.rotation = param1LayoutParams.rotation;
      this.rotationX = param1LayoutParams.rotationX;
      this.rotationY = param1LayoutParams.rotationY;
      this.scaleX = param1LayoutParams.scaleX;
      this.scaleY = param1LayoutParams.scaleY;
      this.transformPivotX = param1LayoutParams.transformPivotX;
      this.transformPivotY = param1LayoutParams.transformPivotY;
      this.translationX = param1LayoutParams.translationX;
      this.translationY = param1LayoutParams.translationY;
      this.translationZ = param1LayoutParams.translationZ;
      this.elevation = param1LayoutParams.elevation;
      this.applyElevation = param1LayoutParams.applyElevation;
    }
    
    private void fillFromConstraints(ConstraintHelper param1ConstraintHelper, int param1Int, Constraints.LayoutParams param1LayoutParams) {
      fillFromConstraints(param1Int, param1LayoutParams);
      if (param1ConstraintHelper instanceof Barrier) {
        this.mHelperType = 1;
        param1ConstraintHelper = param1ConstraintHelper;
        this.mBarrierDirection = param1ConstraintHelper.getType();
        this.mReferenceIds = param1ConstraintHelper.getReferencedIds();
      } 
    }
    
    public void applyTo(ConstraintLayout.LayoutParams param1LayoutParams) {
      param1LayoutParams.leftToLeft = this.leftToLeft;
      param1LayoutParams.leftToRight = this.leftToRight;
      param1LayoutParams.rightToLeft = this.rightToLeft;
      param1LayoutParams.rightToRight = this.rightToRight;
      param1LayoutParams.topToTop = this.topToTop;
      param1LayoutParams.topToBottom = this.topToBottom;
      param1LayoutParams.bottomToTop = this.bottomToTop;
      param1LayoutParams.bottomToBottom = this.bottomToBottom;
      param1LayoutParams.baselineToBaseline = this.baselineToBaseline;
      param1LayoutParams.startToEnd = this.startToEnd;
      param1LayoutParams.startToStart = this.startToStart;
      param1LayoutParams.endToStart = this.endToStart;
      param1LayoutParams.endToEnd = this.endToEnd;
      param1LayoutParams.leftMargin = this.leftMargin;
      param1LayoutParams.rightMargin = this.rightMargin;
      param1LayoutParams.topMargin = this.topMargin;
      param1LayoutParams.bottomMargin = this.bottomMargin;
      param1LayoutParams.goneStartMargin = this.goneStartMargin;
      param1LayoutParams.goneEndMargin = this.goneEndMargin;
      param1LayoutParams.horizontalBias = this.horizontalBias;
      param1LayoutParams.verticalBias = this.verticalBias;
      param1LayoutParams.circleConstraint = this.circleConstraint;
      param1LayoutParams.circleRadius = this.circleRadius;
      param1LayoutParams.circleAngle = this.circleAngle;
      param1LayoutParams.dimensionRatio = this.dimensionRatio;
      param1LayoutParams.editorAbsoluteX = this.editorAbsoluteX;
      param1LayoutParams.editorAbsoluteY = this.editorAbsoluteY;
      param1LayoutParams.verticalWeight = this.verticalWeight;
      param1LayoutParams.horizontalWeight = this.horizontalWeight;
      param1LayoutParams.verticalChainStyle = this.verticalChainStyle;
      param1LayoutParams.horizontalChainStyle = this.horizontalChainStyle;
      param1LayoutParams.constrainedWidth = this.constrainedWidth;
      param1LayoutParams.constrainedHeight = this.constrainedHeight;
      param1LayoutParams.matchConstraintDefaultWidth = this.widthDefault;
      param1LayoutParams.matchConstraintDefaultHeight = this.heightDefault;
      param1LayoutParams.matchConstraintMaxWidth = this.widthMax;
      param1LayoutParams.matchConstraintMaxHeight = this.heightMax;
      param1LayoutParams.matchConstraintMinWidth = this.widthMin;
      param1LayoutParams.matchConstraintMinHeight = this.heightMin;
      param1LayoutParams.matchConstraintPercentWidth = this.widthPercent;
      param1LayoutParams.matchConstraintPercentHeight = this.heightPercent;
      param1LayoutParams.orientation = this.orientation;
      param1LayoutParams.guidePercent = this.guidePercent;
      param1LayoutParams.guideBegin = this.guideBegin;
      param1LayoutParams.guideEnd = this.guideEnd;
      param1LayoutParams.width = this.mWidth;
      param1LayoutParams.height = this.mHeight;
      if (Build.VERSION.SDK_INT >= 17) {
        param1LayoutParams.setMarginStart(this.startMargin);
        param1LayoutParams.setMarginEnd(this.endMargin);
      } 
      param1LayoutParams.validate();
    }
    
    public Constraint clone() {
      Constraint constraint = new Constraint();
      constraint.mIsGuideline = this.mIsGuideline;
      constraint.mWidth = this.mWidth;
      constraint.mHeight = this.mHeight;
      constraint.guideBegin = this.guideBegin;
      constraint.guideEnd = this.guideEnd;
      constraint.guidePercent = this.guidePercent;
      constraint.leftToLeft = this.leftToLeft;
      constraint.leftToRight = this.leftToRight;
      constraint.rightToLeft = this.rightToLeft;
      constraint.rightToRight = this.rightToRight;
      constraint.topToTop = this.topToTop;
      constraint.topToBottom = this.topToBottom;
      constraint.bottomToTop = this.bottomToTop;
      constraint.bottomToBottom = this.bottomToBottom;
      constraint.baselineToBaseline = this.baselineToBaseline;
      constraint.startToEnd = this.startToEnd;
      constraint.startToStart = this.startToStart;
      constraint.endToStart = this.endToStart;
      constraint.endToEnd = this.endToEnd;
      constraint.horizontalBias = this.horizontalBias;
      constraint.verticalBias = this.verticalBias;
      constraint.dimensionRatio = this.dimensionRatio;
      constraint.editorAbsoluteX = this.editorAbsoluteX;
      constraint.editorAbsoluteY = this.editorAbsoluteY;
      constraint.horizontalBias = this.horizontalBias;
      constraint.horizontalBias = this.horizontalBias;
      constraint.horizontalBias = this.horizontalBias;
      constraint.horizontalBias = this.horizontalBias;
      constraint.horizontalBias = this.horizontalBias;
      constraint.orientation = this.orientation;
      constraint.leftMargin = this.leftMargin;
      constraint.rightMargin = this.rightMargin;
      constraint.topMargin = this.topMargin;
      constraint.bottomMargin = this.bottomMargin;
      constraint.endMargin = this.endMargin;
      constraint.startMargin = this.startMargin;
      constraint.visibility = this.visibility;
      constraint.goneLeftMargin = this.goneLeftMargin;
      constraint.goneTopMargin = this.goneTopMargin;
      constraint.goneRightMargin = this.goneRightMargin;
      constraint.goneBottomMargin = this.goneBottomMargin;
      constraint.goneEndMargin = this.goneEndMargin;
      constraint.goneStartMargin = this.goneStartMargin;
      constraint.verticalWeight = this.verticalWeight;
      constraint.horizontalWeight = this.horizontalWeight;
      constraint.horizontalChainStyle = this.horizontalChainStyle;
      constraint.verticalChainStyle = this.verticalChainStyle;
      constraint.alpha = this.alpha;
      constraint.applyElevation = this.applyElevation;
      constraint.elevation = this.elevation;
      constraint.rotation = this.rotation;
      constraint.rotationX = this.rotationX;
      constraint.rotationY = this.rotationY;
      constraint.scaleX = this.scaleX;
      constraint.scaleY = this.scaleY;
      constraint.transformPivotX = this.transformPivotX;
      constraint.transformPivotY = this.transformPivotY;
      constraint.translationX = this.translationX;
      constraint.translationY = this.translationY;
      constraint.translationZ = this.translationZ;
      constraint.constrainedWidth = this.constrainedWidth;
      constraint.constrainedHeight = this.constrainedHeight;
      constraint.widthDefault = this.widthDefault;
      constraint.heightDefault = this.heightDefault;
      constraint.widthMax = this.widthMax;
      constraint.heightMax = this.heightMax;
      constraint.widthMin = this.widthMin;
      constraint.heightMin = this.heightMin;
      constraint.widthPercent = this.widthPercent;
      constraint.heightPercent = this.heightPercent;
      constraint.mBarrierDirection = this.mBarrierDirection;
      constraint.mHelperType = this.mHelperType;
      int[] arrayOfInt = this.mReferenceIds;
      if (arrayOfInt != null)
        constraint.mReferenceIds = Arrays.copyOf(arrayOfInt, arrayOfInt.length); 
      constraint.circleConstraint = this.circleConstraint;
      constraint.circleRadius = this.circleRadius;
      constraint.circleAngle = this.circleAngle;
      constraint.mBarrierAllowsGoneWidgets = this.mBarrierAllowsGoneWidgets;
      return constraint;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/constraintlayout/widget/ConstraintSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */