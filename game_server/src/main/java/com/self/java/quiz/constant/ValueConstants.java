package com.self.java.quiz.constant;

/**
 * @author pjk
 * @date 2018-04-25 9:39
 * @since
 */
public interface ValueConstants {

    /**
     * 奖品的每个位置代表的奖品类型
     */
     interface AWARD_TYPE{
         /**位置1奖品类型为1*/
        int  POSITION_ONE = 1;
        /**位置2奖品类型为2*/
        int  POSITION_TOW = 2;
        /**位置3奖品类型为3*/
        int  POSITION_THREE = 3;
        /**位置4奖品类型为4*/
        int  POSITION_FOUR = 4;
        /**位置5奖品类型为5*/
        int  POSITION_FIVE = 5;
        /**位置6奖品类型为6*/
        int POSITION_SIX = 6;
        /**位置7奖品类型为7*/
        int POSITION_SEVEN = 7;
        /**位置8奖品类型为8*/
        int POSITION_EIGHT = 8;
        /**位置9奖品类型为9*/
        int POSITION_NINE = 9;
    }

    interface HEGEMONY_PARAM{
         /**限制数量*/
         int LIMIT_AMOUNT = 100;
         /**机器人人数*/
         int AWARD_AMOUNT = 79;

    }

}
