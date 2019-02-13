package com.qql.lifting.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private String mTitle;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView card_title_tv = new TextView(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        card_title_tv.setLayoutParams(params);
        card_title_tv.setGravity(Gravity.CENTER);
        card_title_tv.setText("作者：宁浩\n" +
                "链接：https://www.zhihu.com/question/27316773/answer/587039848\n" +
                "来源：知乎\n" +
                "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n" +
                "\n" +
                "大家好，我是宁浩，老有人问我和他们俩怎么认识的，今天我就和大家说说。我和徐峥的故事是从《疯狂的石头》开始的，当时徐峥特别红，刚演完《春光灿烂猪八戒》。我本来给小陶虹写了一封信，希望她来演道哥的女朋友。结果小陶虹收了这封信之后，没看剧本，徐峥看了，说这个故事好，她不来我来，徐峥抢了他媳妇的剧本，就来了。我们两家那个时候就开始熟悉了。徐峥那时候纯属来帮忙，十几天，说不要钱，我们还不好意思，给他包了一个红包。后来拍《疯狂的赛车》的时候徐峥也来帮忙，演一个墓地的经理。我记得临走的时候徐峥说，你下次要找我，少了六十场戏不要跟我开口。我说好啊，后来我给徐峥打电话，说有个戏给你，九十多场。他也纳闷，怎么这么多戏？我说从头到尾就你一人，男一号。徐峥非常激动，接下来《无人区》。多说两句老徐，其实徐峥在我的电影里，是一个高度社会化的符号，是很城市人的形象，不土。中国的很多演员，包括我自己，其实都是有乡土气息的，我们身上残存了很多农耕文明的影子。但是上海、香港这种地方培养出来的演员，是非常城市化的，非常有城市气息。徐峥就是这么个人。和黄渤合作是《疯狂的石头》，在这之前我就知道他，包括管虎这些人大家一起都熟悉，但是这次有机会一起拍戏了。黄渤刚见到我时也特好玩，当时我才20几岁一小伙子，他过来看我们这架势，怎么看怎么觉得像学生作品。因为当时徐峥也很红，他还调侃老徐：“猪八戒来了嘿！”后来黄渤还演了孙悟空，后来发现，当时我找的是大师兄和二师兄拍的《疯狂的石头》。其实很多人不知道，拍这个片子，除了过来帮忙的徐峥没拿钱外，黄渤当时的薪酬也就一万。对于演员来说，那个年代拍电影还是一件很受尊重的事，可以为了艺术少要一些。很多人都对黄渤最后跑的戏份津津乐道。这场戏本来被剪的特别短，但我重新改了回来，因为我觉得老黄的角色就代表了一种中国底层的生命力。最后的效果你们也都知道。我们三个就是这么开始的。");
        return card_title_tv;
    }
}