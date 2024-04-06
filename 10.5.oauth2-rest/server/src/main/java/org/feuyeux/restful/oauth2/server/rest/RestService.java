package org.feuyeux.restful.oauth2.server.rest;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.feuyeux.restful.oauth2.common.domain.Tarot;
import org.feuyeux.restful.oauth2.common.domain.Tarots;
import org.springframework.stereotype.Service;

@Service
@Path("/")
public class RestService {
  private Tarots tarots;

  @GET
  @Path("/tarots")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Tarots getTarots() {
    return tarots;
  }

  @PostConstruct
  public void init() {
    loadTarots();
  }

  public void loadTarots() {
    if (tarots == null) {
      tarots = new Tarots();
      List<Tarot> list = new ArrayList<>();
      Tarot tarot = new Tarot();
      tarot.setCard("魔法师");
      tarot.setV("原创性,创造力,想像力,自我依靠,自然发生,自信,足智多谋,灵活,行家,自我控制,欺骗,手的灵巧");
      tarot.setRv("意志的软弱,笨拙,不安定,焦躁不安,延迟,使用技巧造成破坏性的结果");
      list.add(tarot);

      tarot.setCard("女祭司");
      tarot.setV("智慧,明智的判断,常识,平静沈著（平和）,客观性,洞察力,远见（先见之明）,直觉,感受力,自我依靠,无感情、冷漠,柏拉图式的情谊");
      tarot.setRv("无知（不学无术）,没有远见（目光短浅、缺乏远见）,自私, 热情, 身体上的冲动, 接受肤浅的知识, 不当的判断");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("皇后");
      tarot.setV("女性的进步,丰腴多产的,成就,母亲,姊妹,妻子,婚姻,小孩,女性的影响力,有能力激发他人,实际,直觉");
      tarot.setRv("犹豫不决、踌躇, 不活动、无为, 怠惰, 缺乏兴趣, 缺乏专注力, 犹豫不决,延迟、耽搁,焦虑,贫瘠,不贞");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("皇帝");
      tarot.setV(
          "世俗的权力, 自信, 富有, 稳定, 权威, 不屈不挠的意志（不服输的精神）,好战的倾向, 父亲, 兄弟, 丈夫, 男性的影响力, 智能和理性的优势凌驾情绪与激情, 父权体制");
      tarot.setRv("不成熟,无效用,优柔寡断,不行动,性格弱点,无法控制繁杂的情绪");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("教皇");
      tarot.setV(
          "墨守仪式, 仁慈, 善良, 宽恕, 灵感, 怜悯同情, 奴隶状态, 不活动, 羞怯, 刻意保留, 受困於自我的思想中, 谨守过时的观念和原则, 顺从, 信仰或精神上的领导者");
      tarot.setRv("愚蠢的慷慨行径,容易被感动和影响的特质,无能,易受伤的要害,脆弱,异端,放弃");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("恋人");
      tarot.setV("爱情, 美丽, 完美, 和谐, 信任, 罗曼史的开始, 深情, 乐观主义, 情绪自由, 考验或受试炼的必要, 挣扎於神圣与世俗的爱之间, 有意义的恋情");
      tarot.setRv("经不起考验,不可靠,分离,爱情和婚姻中的挫折,他人的干涉和阻挠,善变,不值得信赖的,不智的计画");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("战车");
      tarot.setV("逆境～或许已经克服了, 冲突的影响, 骚动, 报复, 成功, 或许有一段航行或旅程, 逃脱, 急著作决定, 需要注意细节, 迫切要控制个人的情绪");
      tarot.setRv("失败,在最后一刻竟失去了原本的把握,计画的突然失败,被击垮了,无法面对现实");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("力量");
      tarot.setV("力量, 勇气, 坚信不移, 能源, 决心, 挑战, 行动, 自信, 热心, 事物凌驾心智不然就是心智凌驾事物, 成就");
      tarot.setRv("虚弱,琐碎,无能,生病,缺乏信心,滥用力量,屈服於诱惑,漠不关心");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("隐士");
      tarot.setV("商劝, 知识, 挂念, 谨慎, 周到, 警惕, 夙夜匪懈, 慎重, 自我否定, 退缩, 退却, 取消, 隐藏感情的倾向, 害怕发现");
      tarot.setRv("不谨慎,急切,轻率,贸然过早,不当的劝告,迟钝造成的失败,过度谨慎导致不必要的拖延");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("命运之轮");
      tarot.setV(
          "命运, 幸运, 命数, 结果, 颠峰, 达到问题的终点, 好运或坏运～依据邻近牌的影响而定无可逃避的, 命运之轮表示的是事件自始至终的情况, 无论更好或更坏都要前进");
      tarot.setRv("坏运, 破坏的顺序, 缘於预料外事件的中断和抵触, 没有料到的外在因素, 未考虑到的外在影响");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("正义");
      tarot.setV("合理性,正义,适度的均衡,和谐,公正,正当,美德,信誉,处女贞节,正当报酬,个人牵涉的最终结局将是真正公平的～无论喜欢或不喜欢,均衡对等状态,持平,公平");
      tarot.setRv("偏见, 诬告, 偏执, 严格的审判, 无法容忍, 不公平, 枉法妄为");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("吊人");
      tarot.setV(
          "生活在悬宕中,过渡时期,转变,人生道路上的想法的转折,冷漠和单调,遗弃,放弃,生命力的变化,在重大事件之间的缓冲时期,牺牲,悔改,调整更新,重生,新的生命力来临");
      tarot.setRv("不想牺牲, 不情愿做必须的努力, 无法奉献出自己, 全神贯注於自我, 不正确的预言, 无益的牺牲");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("死神");
      tarot.setV("蜕变,除旧布新,意料之外的改变,损失,变更,旧有的自我的突然改变～但不必然是肉体的死亡,熟悉的境遇或友谊的结束,财务损失,新纪元的开始,疾病,可能的死亡");
      tarot.setRv("停滞淤塞, 固定性（静止）,缓慢的转变, 部份的改变, 迟钝, 严密避免重大事故");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("节制");
      tarot.setV("和缓,节制,耐心,透过自制和俭朴达到成就,调节,和谐,混合或揍合成完美的联合经营管理, 融合, 调适, 良好的影响, 团结, 成功的结合");
      tarot.setRv("不调和,利益冲突,敌对,无法与他人工作,对了解他人有困难,没耐性,贫瘠不育");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("魔鬼");
      tarot.setV(
          "附庸, 蹂躏, 束缚, 恶毒, 奉承, 没落, 未成功, 玄怪的体验, 不良的外在影响或警告, 黑魔法, 意料之外的失败, 无能实现他的理想, 暴力, 震惊, 意外事故, 自我惩罚, 魔鬼的引诱, "
              + "自我毁灭");
      tarot.setRv("摆脱束缚,抛弃枷锁,离异,透过他人认清自己的需要,克服重大难关,精神性体悟的开始");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("塔");
      tarot.setV("全盘和突然的转变, 打破陈旧观念, 舍弃以往的关系, 切断一份友谊, 改变某人的观感, 意料之外的事件, 破产, 垮台（没落）,失去安定, 失去保障");
      tarot.setRv("被持续的压迫,因循古法,制式的生活,无力导向任何有价值的改变,受困於不快乐的情境");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("星星");
      tarot.setV(
          "希望, 信心, 灵感, 光明的前景, 过去和现在的交融, 乐观, 洞察力, 好预兆, 精神性的恋爱, 星象学上的影响, 满足, 愉悦, 在欲望和工作、希望和成果、爱情和表达上的适度均衡");
      tarot.setRv("未实现的愿望,失望,悲观,坏运气,缺乏机运,顽固,牛脾气,不平衡,结束一段不满意的事业经验或友谊");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("月亮");
      tarot.setV(
          "欺诈迷惑, 朦胧晦暗, 暧昧不明, 直觉, 醒悟（理想破灭）,危险, 坏影响, 别有用心的动机, 虚假的朋友, 自私, 骗局, 狡滑诡诈, 不光彩的, 中伤, 诽谤, 表面情况, 未知的敌人");
      tarot.setRv("在伤害发生前识破了小欺诈,无足轻重的错误,克服不当的诱惑,不劳而获,占某人的便宜");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("太阳");
      tarot.setV(
          "满意, 成就, 知足, 成功, 亲密关系, 爱情, 欢喜, 热爱, 无私的心境, 约定, 婚约、订婚, 快乐的婚姻, 日常生活中的愉悦, 一位好朋友, 精神奕奕, 温暖, 真诚, 源於单纯事物的喜悦,"
              + " 艺术方面有所成就, 自由自在");
      tarot.setRv("不快乐,孤单寂寞,或许是破裂的盟约或婚姻,取消的计画,胜利延迟～尽管并不必然是全面的损失,阴霾的未来");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("审判");
      tarot.setV(
          "赎罪, 审判, 悔改和原谅的需要, 该衡量我们如何利用机会的时候, 活化, 重生, 改善, 进展, 升级, 长生不老的欲望, 礼遇的法律判决, 应仔细考量目前的行动会如何影响他人");
      tarot.setRv("延迟,失望,无法面对现实,优柔寡断,离异,耽误,盗窃,感情的疏远");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("世界");
      tarot.setV("依附, 完成, 完美, 终极的改变, 一切努力的结果, 成功, 综合体, 实现, 才能, 胜任职责, 辛勤工作的报酬, 永生, 他人的赞赏");
      tarot.setRv("不完美、缺点,功亏一篑,未能完成所发起的事务,没有远见,失望");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币国王");
      tarot.setV("经验丰富而成功的领导人,有品格和智慧的人,商业敏锐度,数理能力,忠诚的朋友,可靠的结婚对象,明智的投资,有能力获取金钱和珍贵的财物");
      tarot.setRv("腐败堕落, 为达目的不择手段, 恶行, 贪欲, 不忠诚, 一个老不修, 冒险犯难, 不知节俭的");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币王后");
      tarot.setV("繁荣兴旺, 安康, 富足, 财富, 极为舒坦, 慷慨, 安全, 自由, 华贵, 优雅, 尊严, 富有却慷慨仁慈的人, 高尚的灵魂");
      tarot.setRv("假的好现象,挂念,疑心,被忽视的责任,不值信任的人,失败的恐惧");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币骑士");
      tarot.setV("成熟而可靠的人,可信赖的,有办事方法的,耐性,坚持不懈的,达成任务的能力,勤奋的,有组织的");
      tarot.setRv("停滞, 惰性, 缺乏决断或方向, 心胸狭窄, 限制於教条观念中, 无所事事（赋闲）");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币随从");
      tarot.setV("深度的专注和用心, 学术成就, 反映, 渴望有新的点子, 不顾实际的社会改良家, 携带讯息的人");
      tarot.setRv("不切实际的人,无法承认显而易见的事实,想法的纷乱,不合逻辑的思考,反叛,挥霍浪费,不利的消息");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币十");
      tarot.setV("富有,安全,家务事,血统,继承,家");
      tarot.setRv("差的机运, 可能的损失, 冒险犯难, 抢劫, 失去继承权, 放荡, 赌博");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币九");
      tarot.setV("成就, 洞察力, 辨别, 周到, 远见（先见之明）,谨慎, 物质上的富足, 对大自然之爱");
      tarot.setRv("捣蛋恶搞,危险,风暴,意图不轨,或许会失去一位好友或珍贵的财物");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币八");
      tarot.setV("学徒,技术艺能,学习快速,坦率,谦逊,手工艺品,个人的努力");
      tarot.setRv("缺乏雄心壮志, 虚荣, 自大自满, 醒悟（理想破灭）,高利贷, 伪善, 阿谀谄媚, 密谋策略");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币七");
      tarot.setV("心灵手巧, 成长, 努力工作, 进步, 成功的交易, 金钱, 宝物");
      tarot.setRv("无耐性,局促不安,不谨慎的行动,金钱的损失,不明智的投资");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币六");
      tarot.setV("慷慨,慈善行为,施舍,善良,报酬,礼物,物质上的获得");
      tarot.setRv("贪婪, 自私自利, 羡慕, 嫉妒, 呆帐, 负债");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币五");
      tarot.setV("穷困, 损失, 失败, 错误, 情妇, 爱人");
      tarot.setRv("坏倾向的逆转,事务上的新兴趣,克服倾覆,婚姻或爱情关系不和谐");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币四");
      tarot.setV("对物质财富的喜爱,囤积者,放高利贷者,吝啬鬼,无法与人分享");
      tarot.setRv("物质事务方面的挫败, 反对进一步获得, 疑虑和耽搁, 挥霍无度的人");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币三");
      tarot.setV("极强的商业或工作技能, 优异, 完美, 美术才能, 尊严, 名声, 层级, 权力");
      tarot.setRv("情感脆弱,平庸,财务问题,司空见惯的意见,欠缺技巧,全神贯注");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币二");
      tarot.setV("执行新计画时的困境,困难的情况,新的麻烦,难堪");
      tarot.setRv("文学才能, 处事才干, 假装的快乐, 勉强的欢颜, 信件, 消息");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("金币ACE");
      tarot.setV("完美, 达成, 富庶, 幸福, 至喜, 黄金, 有价值的硬币或人工制品, 宝物, 物质上和精神上满足的结合");
      tarot.setRv("富庶但不快乐,不当使用财富,浪费的金钱,为金钱而堕落,吝啬,贪心,愚人金");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯国王");
      tarot.setV("责任感和创意,有学养的人,专业的,商人,律师,虔诚的人,科学家,体贴周到的人,善良而可靠,开明宽大的作为,艺术家,对科学和艺术感兴趣,慷慨大方");
      tarot.setRv("艺术家性格, 双面的交易, 丑闻, 损失, 毁灭, 不公正, 诡诈而无美德可言的人");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯王后");
      tarot.setV("热心和公正的人, 被锺爱和敬重, 好朋友和好母亲, 奉献的妻子, 实际, 正直, 爱心和智慧, 远见的天赋");
      tarot.setRv("不正直,不可信赖,恶行");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯骑士");
      tarot.setV("即将面临一个邀请或机会,抵达,接近,前进,吸引,劝诱,恳求,挑战,提案");
      tarot.setRv("暗中, 手段, 欺骗, 狡猾奸诈的人, 有诈骗能力的人");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯随从");
      tarot.setV("勤勉好学和专心致志的人, 反映的, 凝思默想, 忠诚, 自动自发地提供服务朝向特定目标努力, 有助益的人, 值得信赖的工作人员");
      tarot.setRv("倾斜,偏差越轨,脆弱易感的,暂时的纷乱焦躁,引诱,阿谀谄媚者");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯十");
      tarot.setV("家庭,欢喜,愉悦,和平,爱情,满足,美好的家庭,荣誉,尊重,美德");
      tarot.setRv("失去友谊, 不快乐, 家庭纷争, 琐碎, 盛怒, 意见的差异");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯九");
      tarot.setV("成功, 物质上的成就, 利益, 安宁, 富足安康, 丰富充沛, 健康良好, 克服困难");
      tarot.setRv("错误,物质上的损失,不完美,不当、错误的信任,虚假的自由,对立,差异");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯八");
      tarot.setV("中止努力,失望,放弃先前的计画,羞怯,谦逊,扬弃成功");
      tarot.setRv("努力不懈直到完全成功, 节庆, 兴高采烈");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯七");
      tarot.setV("幻想, 想像力, 白日梦, 愚蠢的念头, 充满渴望的想法, 虚幻的成功");
      tarot.setRv("欲望,决心,强大的意志力,接近达成的目标,明智的选择");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯六");
      tarot.setV("回忆,过往的影响,已经消逝的事物,飞逝的童年,乡愁,褪色的印象");
      tarot.setRv("未来, 在前端的机会, 将来的事件, 新的展望, 计画可能失败, 即将来临的");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯五");
      tarot.setV("部分损失, 后悔, 没有真正意义的友谊, 没有真正爱情的婚姻, 继承, 不完整的合结合或合夥关系");
      tarot.setRv("充满希望的远景,美好的期许,新的联盟,亲近关系,老友归来,复合、重聚");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯四");
      tarot.setV("疲倦,反感,厌恶,失望,难堪的经验,人生中的停摆阶段");
      tarot.setRv("新的可能性, 新的人际关系, 对旧问题的新见解, 新知识");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯三");
      tarot.setV("问题的解决, 结论, 安慰, 治疗, 满意的结果, 妥协共识");
      tarot.setRv("过渡享乐,过剩,奢侈品,失去声望,延迟,缺乏感恩");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯二");
      tarot.setV("爱情,新的友谊或是重修旧好,热情,结合,婚约,了解,合作,合夥关系,婚姻");
      tarot.setRv("不满足的爱, 虚假的友谊, 麻烦的关系, 离异, 分离, 交错的欲望, 对立, 误解");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("圣杯ACE");
      tarot.setV("充沛丰富, 满足, 完美, 欢喜, 丰腴, 财富, 丰饶多产, 美丽和愉悦, 满溢著良善, 美好的远景");
      tarot.setRv("转变,侵蚀,不安稳,贫乏,无回报的爱,不尽情的欢乐,虚假的心,矛盾不一致");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖国王");
      tarot.setV("正直而诚恳的人,成熟,智慧,忠实的,友善的,有同情心的,受过教育的,一位绅士,通常是已婚的人,父亲般的（慈祥）");
      tarot.setRv("严格, 苛刻, 稍嫌极端和夸张的想法, 墨守成规的, 深思熟虑的");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖王后");
      tarot.setV("富同情心和谅解的人, 友善的, 深情的, 纯洁贞节的, 实际的, 魅力和优雅, 亲切的女主人, 真诚地对他人感到兴趣关切");
      tarot.setRv("妒忌,欺诈,可能不贞,起伏的情绪,善变,抵抗,对立");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖骑士");
      tarot.setV("一段旅程,前进到未知的领域,改变,飞航,缺乏,居住的变动");
      tarot.setRv("中断, 预料之外的转变, 争吵, 人际关系的破裂, 决裂");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖随从");
      tarot.setV("忠诚和忠心的人, 一位使者, 间谍密探, 能信任的朋友, 意图良好的陌生人, 始终如一的人, 携带重要讯息的人");
      tarot.setRv("优柔寡断,不情愿,不安善变,爱嚼舌根的人（很八卦的人）,携带著坏音讯的人,会使你伤心的人,不愉悦");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖十");
      tarot.setV("过重的压力,问题马上能解决,奋力达成目标或是维持一定的水准或地位,或许会把力量用在自私的目标上");
      tarot.setRv("困难, 阴谋策略, 叛徒, 骗徒, 推托藉口, 会有一些损失");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖九");
      tarot.setV("困难或变动的预料, 期待, 隐藏的敌人, 欺骗, 纪律, 顺序, 当下的挣扎间歇了");
      tarot.setRv("障碍,逆境,问题,延迟,灾难,须克服的阻碍,不健康");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖八");
      tarot.setV("迅速的行动,突然的进展或动作,速度,匆促地下决定,过於快速的进展");
      tarot.setRv("争论之刺（恼怒之处）,嫉妒, 困扰, 停滞不前, 家庭的争执");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖七");
      tarot.setV("成功, 获得, 打破阴霾险境, 优越, 胜利");
      tarot.setRv("惊愕,焦虑,难堪,迟疑导致损失,困惑茫然");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖六");
      tarot.setV("征服,胜利,好消息,进展,期待,付出努力而达成心愿");
      tarot.setRv("无限期的延迟, 恐惧, 挂念, 不忠诚, 表面上的利益, 不确定的获得");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖五");
      tarot.setV("不满足的欲望, 挣扎, 劳力, 暴力的争斗, 障碍");
      tarot.setRv("诡计,自相矛盾,错综复杂,牵累,切莫迟疑");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖四");
      tarot.setV("罗曼史,社会,和谐,新获得的兴盛,平静安宁,努力的果实,挣扎后的喘息");
      tarot.setRv("失去安稳平静, 未完成的恋情, 局促不安, 被消磨的美丽, 不完整的快乐");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖三");
      tarot.setV("实际的知识, 商业敏锐度, 事业, 协商谈判, 交易, 商业");
      tarot.setRv("别有用心的援助,减轻不幸,当心所提供的帮助");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖二");
      tarot.setV("成熟的个体,管理者,达到目标和需要,无畏无惧,负责尽职的勇气,支配型的人格");
      tarot.setRv("伤心, 麻烦, 起因於他人的阻碍, 失去信心, 惊讶");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("权杖ACE");
      tarot.setV("创造, 起始发源, 发明, 一件事的开始, 幸运, 事业, 获得, 继承, 小孩出生, 一段具有意义的经验开始了, 一段冒险, 恶搞");
      tarot.setRv("错误的开始,乌云密布的前景,未实现的目标,衰微,空虚的存在,苦恼,计画的取消");

      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑国王");
      tarot.setV("富有行动力和决断力的人,有经验的,有权威的,控制掌握的,指挥若定（威风、居高临下）,专业人士,具高度分析能力的人,正直,力量,优越");
      tarot.setRv("赶尽杀绝的人, 残酷, 冲突, 自私, 虐待狂, 引发不必要的动乱和悲哀的人, 倔强刚愎");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑皇后");
      tarot.setV(
          "机智的, 极为敏感的, 难以捉摸的人, 或许代表寡妇或是悲伤的女人, 哀悼, 贫困, 缺乏, 孤独, 分离, 曾经有著极度快乐的人但现在体验到了不幸和逆转的焦虑");
      tarot.setRv("心胸狭窄,有恶意的,顽固,欺诈,报复,过於拘谨,险恶的敌人,坏脾气的人");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑骑士");
      tarot.setV("勇气,技巧,能力,年轻人的力量与冲撞,英雄行径,对抗与战争,无畏的急骋冲进未知当中,深谙行动和战事的专家");
      tarot.setRv("无能, 轻率, 为一个女人争吵或毁灭, 冲动造成的错误, 自吹自擂的呆子, 单纯的");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑随从");
      tarot.setV("夙夜匪懈, 敏捷, 刺探, 审慎低调的人, 活力旺盛的年轻人, 柔软的身躯, 这张牌象徵一个人善於察觉、分辨和揭露未知或更不明显的事物, 洞察力");
      tarot.setRv("被发现的冒充者,生病也是有可能的,无力面对更强大的力量,缺乏准备");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑十");
      tarot.setV("毁灭,苦痛,折磨,精神上的痛苦,荒芜寂寞,不幸,失望");
      tarot.setRv("受惠, 利益, 暂时的拥有, 改善, 刹时的成功, 瞬间的优势");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑九");
      tarot.setV("关心, 流产, 为所爱的人担心, 绝望");
      tarot.setRv("怀疑,中伤的流言,耻辱,顾忌,羞怯,阴暗的特质,有理由的恐惧");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑八");
      tarot.setV("紧要关头,冲突,控制,禁锢,骚动,坏消息,批评,生病,诽谤");
      tarot.setRv("过往的背叛, 困难, 辛勤工作, 消沈, 忧虑不安, 意外, 事故");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑七");
      tarot.setV("新计画, 心愿, 坚忍不拔, 毅力, 尽力而为, 希望, 信心, 幻想, 局部的成功");
      tarot.setRv("争执,不明确的建议和劝告,慎重的,中伤,胡言乱语");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑六");
      tarot.setV("旅途和行程,固执的企图克服困难,权宜之行为,焦虑之后的成功");
      tarot.setRv("僵局, 无用的提案, 表白, 宣布");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑五");
      tarot.setV("征服, 击败, 破怀他人, 退化, 敌手可能兴起了, 废止、撤回, 恶名, 不名誉");
      tarot.setRv("不确定的展望,损失或是击败的机会,虚弱,或许有不幸会降临你的朋友,魅惑,埋葬");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑四");
      tarot.setV("延缓,病后的休养,歇息,补充,孤单,放逐,撤退,放弃");
      tarot.setRv("活动, 慎重, 警觉, 经济, 小心的前进, 希望重获所失去的");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑三");
      tarot.setV("缺乏, 悲伤, 失望, 争吵, 调动, 消散, 转移注意, 对立, 分离, 延迟");
      tarot.setRv("注意力散乱,困惑,混乱,不和,错误,误解,不融洽,焦虑,损失,疏远");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑二");
      tarot.setV("均衡的力量,和谐,坚定刚毅,协定（和谐）,抵销的要素,僵局,锺爱");
      tarot.setRv("口是心非, 不实或扭曲的陈述, 不名誉, 背叛变节, 虚伪的朋友, 谎言");
      list.add(tarot);
      tarot = new Tarot();
      tarot.setCard("宝剑ACE");
      tarot.setV("强大的决心, 开创, 力量, 力气, 行动, 极度, 胜利, 权力, 成功, 丰腴, 兴旺, 深层的情感, 爱情, 优胜, 征服");
      tarot.setRv("灾害,专制暴政,不幸,自我毁灭,火爆的脾气,难堪,障碍,贫瘠");
      list.add(tarot);
      tarots.setTarotList(list);
    }
  }
}
