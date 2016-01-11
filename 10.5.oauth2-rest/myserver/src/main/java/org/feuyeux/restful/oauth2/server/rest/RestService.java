package org.feuyeux.restful.oauth2.server.rest;

import org.feuyeux.restful.oauth2.server.Session;
import org.feuyeux.restful.oauth2.server.Speaker;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Path("/")
public class RestService {
    private List<Speaker> speakers;
    private List<Session> sessions;
    private List<Map<String, String>> tarots;

    @GET
    @Path("/tarots")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getTarots() {
        return tarots;
    }

    @GET
    @Path("/speakers")
    @Produces(MediaType.APPLICATION_XML)
    public List<Speaker> getSpeakers() {
        return speakers;
    }

    @GET
    @Path("/speakers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Speaker getSpeaker(@PathParam("id") Long id) {
        for (Speaker s : speakers) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @GET
    @Path("/sessions")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Session> getSessions() {
        return sessions;
    }

    @GET
    @Path("/sessions/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Session getSession(String id) {
        for (Session s : sessions) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    @GET
    @Path("/trusted/message")
    @Produces(MediaType.TEXT_PLAIN)
    @PreAuthorize("#oauth2.clientHasRole('ROLE_CLIENT')")
    public String getTrustedClientMessage() {
        return "Hello, Trusted Client";
    }

    @PostConstruct
    public void init() {
        loadSpeakers();
        loadSessions();
        loadTarots();
    }

    public void loadSpeakers() {
        if (speakers == null) {
            //mock all the conference speakers data
            speakers = new ArrayList<Speaker>();
            speakers.add(new Speaker(1l, "Reza Rahman", "Java Evangelist", "http://confoo.ca/images/speakers/2015/reza-rahman.jpg", "http://twitter.com/reza_rahman", null, "Oracle"));
            speakers.add(new Speaker(2l, "Hanneli Tavante", "Senior Software Developer", "http://confoo.ca/images/speakers/2015/hanneli-tavante.jpg", "http://twitter.com/hannelita", null, "CodeMiner 42"));
            speakers.add(new Speaker(3l, "Rodrigo Cândido da Silva", "Software Architect", "http://confoo.ca/images/speakers/2015/rodrigo-candido-da-silva.jpg", "http://twitter.com/rcandidosilva", null, "Integritas"));
            speakers.add(new Speaker(4l, "Eduardo Shiota", "Senior Front-end Developer", "http://confoo.ca/images/speakers/2015/eduardo-shiota-yasuda.jpg", "http://twitter.com/shiota", null, "Booking.com"));
        }
    }

    public void loadSessions() {
        if (sessions == null) {
            //mock all the conference sessions data
            sessions = new ArrayList<Session>();
            sessions.add(new Session("CONFOO01", "JMS.Next(): JMS 2 and Beyond", null));
            sessions.add(new Session("CONFOO02", "Java EE 8 - Future, Wishes and Predictions", null));
            sessions.add(new Session("CONFOO03", "Supporting Multi-Tenancy Applications with Java EE", null));
            sessions.add(new Session("CONFOO04", "Modular JavaScript Heaven with AMD and Events", null));

        }
    }

    public void loadTarots() {
        if (tarots == null) {
            tarots = new ArrayList<Map<String, String>>();

            Map<String, String> map = new HashMap<String, String>();
            map.put("排", "魔法师");
            map.put("正", "原创性,创造力,想像力,自我依靠,自然发生,自信,足智多谋,灵活,行家,自我控制,欺骗,手的灵巧");
            map.put("逆", "意志的软弱,笨拙,不安定,焦躁不安,延迟,使用技巧造成破坏性的结果");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "女祭司");
            map.put("正", "智慧,明智的判断,常识,平静沈著（平和）,客观性,洞察力,远见（先见之明）,直觉,感受力,自我依靠,无感情、冷漠,柏拉图式的情谊");
            map.put("逆", "无知（不学无术）,没有远见（目光短浅、缺乏远见）,自私, 热情, 身体上的冲动, 接受肤浅的知识, 不当的判断");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "皇后");
            map.put("正", "女性的进步,丰腴多产的,成就,母亲,姊妹,妻子,婚姻,小孩,女性的影响力,有能力激发他人,实际,直觉");
            map.put("逆", "犹豫不决、踌躇, 不活动、无为, 怠惰, 缺乏兴趣, 缺乏专注力, 犹豫不决,延迟、耽搁,焦虑,贫瘠,不贞");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "皇帝");
            map.put("正", "世俗的权力, 自信, 富有, 稳定, 权威, 不屈不挠的意志（不服输的精神）,好战的倾向, 父亲, 兄弟, 丈夫, 男性的影响力, 智能和理性的优势凌驾情绪与激情, 父权体制");
            map.put("逆", "不成熟,无效用,优柔寡断,不行动,性格弱点,无法控制繁杂的情绪");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "教皇");
            map.put("正", "墨守仪式, 仁慈, 善良, 宽恕, 灵感, 怜悯同情, 奴隶状态, 不活动, 羞怯, 刻意保留, 受困於自我的思想中, 谨守过时的观念和原则, 顺从, 信仰或精神上的领导者");
            map.put("逆", "愚蠢的慷慨行径,容易被感动和影响的特质,无能,易受伤的要害,脆弱,异端,放弃");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "恋人");
            map.put("正", "爱情, 美丽, 完美, 和谐, 信任, 罗曼史的开始, 深情, 乐观主义, 情绪自由, 考验或受试炼的必要, 挣扎於神圣与世俗的爱之间, 有意义的恋情");
            map.put("逆", "经不起考验,不可靠,分离,爱情和婚姻中的挫折,他人的干涉和阻挠,善变,不值得信赖的,不智的计画");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "战车");
            map.put("正", "逆境～或许已经克服了, 冲突的影响, 骚动, 报复, 成功, 或许有一段航行或旅程, 逃脱, 急著作决定, 需要注意细节, 迫切要控制个人的情绪");
            map.put("逆", "失败,在最后一刻竟失去了原本的把握,计画的突然失败,被击垮了,无法面对现实");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "力量");
            map.put("正", "力量, 勇气, 坚信不移, 能源, 决心, 挑战, 行动, 自信, 热心, 事物凌驾心智不然就是心智凌驾事物, 成就");
            map.put("逆", "虚弱,琐碎,无能,生病,缺乏信心,滥用力量,屈服於诱惑,漠不关心");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "隐士");
            map.put("正", "商劝, 知识, 挂念, 谨慎, 周到, 警惕, 夙夜匪懈, 慎重, 自我否定, 退缩, 退却, 取消, 隐藏感情的倾向, 害怕发现");
            map.put("逆", "不谨慎,急切,轻率,贸然过早,不当的劝告,迟钝造成的失败,过度谨慎导致不必要的拖延");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "命运之轮");
            map.put("正", "命运, 幸运, 命数, 结果, 颠峰, 达到问题的终点, 好运或坏运～依据邻近牌的影响而定无可逃避的, 命运之轮表示的是事件自始至终的情况, 无论更好或更坏都要前进");
            map.put("逆", "坏运, 破坏的顺序, 缘於预料外事件的中断和抵触, 没有料到的外在因素, 未考虑到的外在影响");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "正义");
            map.put("正", "合理性,正义,适度的均衡,和谐,公正,正当,美德,信誉,处女贞节,正当报酬,个人牵涉的最终结局将是真正公平的～无论喜欢或不喜欢,均衡对等状态,持平,公平");
            map.put("逆", "偏见, 诬告, 偏执, 严格的审判, 无法容忍, 不公平, 枉法妄为");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "吊人");
            map.put("正", "生活在悬宕中,过渡时期,转变,人生道路上的想法的转折,冷漠和单调,遗弃,放弃,生命力的变化,在重大事件之间的缓冲时期,牺牲,悔改,调整更新,重生,新的生命力来临");
            map.put("逆", "不想牺牲, 不情愿做必须的努力, 无法奉献出自己, 全神贯注於自我, 不正确的预言, 无益的牺牲");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "死神");
            map.put("正", "蜕变,除旧布新,意料之外的改变,损失,变更,旧有的自我的突然改变～但不必然是肉体的死亡,熟悉的境遇或友谊的结束,财务损失,新纪元的开始,疾病,可能的死亡");
            map.put("逆", "停滞淤塞, 固定性（静止）,缓慢的转变, 部份的改变, 迟钝, 严密避免重大事故");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "节制");
            map.put("正", "和缓,节制,耐心,透过自制和俭朴达到成就,调节,和谐,混合或揍合成完美的联合经营管理, 融合, 调适, 良好的影响, 团结, 成功的结合");
            map.put("逆", "不调和,利益冲突,敌对,无法与他人工作,对了解他人有困难,没耐性,贫瘠不育");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "魔鬼");
            map.put("正", "附庸, 蹂躏, 束缚, 恶毒, 奉承, 没落, 未成功, 玄怪的体验, 不良的外在影响或警告, 黑魔法, 意料之外的失败, 无能实现他的理想, 暴力, 震惊, 意外事故, 自我惩罚, 魔鬼的引诱, 自我毁灭");
            map.put("逆", "摆脱束缚,抛弃枷锁,离异,透过他人认清自己的需要,克服重大难关,精神性体悟的开始");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "塔");
            map.put("正", "全盘和突然的转变, 打破陈旧观念, 舍弃以往的关系, 切断一份友谊, 改变某人的观感, 意料之外的事件, 破产, 垮台（没落）,失去安定, 失去保障");
            map.put("逆", "被持续的压迫,因循古法,制式的生活,无力导向任何有价值的改变,受困於不快乐的情境");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "星星");
            map.put("正", "希望, 信心, 灵感, 光明的前景, 过去和现在的交融, 乐观, 洞察力, 好预兆, 精神性的恋爱, 星象学上的影响, 满足, 愉悦, 在欲望和工作、希望和成果、爱情和表达上的适度均衡");
            map.put("逆", "未实现的愿望,失望,悲观,坏运气,缺乏机运,顽固,牛脾气,不平衡,结束一段不满意的事业经验或友谊");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "月亮");
            map.put("正", "欺诈迷惑, 朦胧晦暗, 暧昧不明, 直觉, 醒悟（理想破灭）,危险, 坏影响, 别有用心的动机, 虚假的朋友, 自私, 骗局, 狡滑诡诈, 不光彩的, 中伤, 诽谤, 表面情况, 未知的敌人");
            map.put("逆", "在伤害发生前识破了小欺诈,无足轻重的错误,克服不当的诱惑,不劳而获,占某人的便宜");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "太阳");
            map.put("正", "满意, 成就, 知足, 成功, 亲密关系, 爱情, 欢喜, 热爱, 无私的心境, 约定, 婚约、订婚, 快乐的婚姻, 日常生活中的愉悦, 一位好朋友, 精神奕奕, 温暖, 真诚, 源於单纯事物的喜悦, 艺术方面有所成就, 自由自在");
            map.put("逆", "不快乐,孤单寂寞,或许是破裂的盟约或婚姻,取消的计画,胜利延迟～尽管并不必然是全面的损失,阴霾的未来");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "审判");
            map.put("正", "赎罪, 审判, 悔改和原谅的需要, 该衡量我们如何利用机会的时候, 活化, 重生, 改善, 进展, 升级, 长生不老的欲望, 礼遇的法律判决, 应仔细考量目前的行动会如何影响他人");
            map.put("逆", "延迟,失望,无法面对现实,优柔寡断,离异,耽误,盗窃,感情的疏远");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "世界");
            map.put("正", "依附, 完成, 完美, 终极的改变, 一切努力的结果, 成功, 综合体, 实现, 才能, 胜任职责, 辛勤工作的报酬, 永生, 他人的赞赏");
            map.put("逆", "不完美、缺点,功亏一篑,未能完成所发起的事务,没有远见,失望");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币国王");
            map.put("正", "经验丰富而成功的领导人,有品格和智慧的人,商业敏锐度,数理能力,忠诚的朋友,可靠的结婚对象,明智的投资,有能力获取金钱和珍贵的财物");
            map.put("逆", "腐败堕落, 为达目的不择手段, 恶行, 贪欲, 不忠诚, 一个老不修, 冒险犯难, 不知节俭的");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币王后");
            map.put("正", "繁荣兴旺, 安康, 富足, 财富, 极为舒坦, 慷慨, 安全, 自由, 华贵, 优雅, 尊严, 富有却慷慨仁慈的人, 高尚的灵魂");
            map.put("逆", "假的好现象,挂念,疑心,被忽视的责任,不值信任的人,失败的恐惧");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币骑士");
            map.put("正", "成熟而可靠的人,可信赖的,有办事方法的,耐性,坚持不懈的,达成任务的能力,勤奋的,有组织的");
            map.put("逆", "停滞, 惰性, 缺乏决断或方向, 心胸狭窄, 限制於教条观念中, 无所事事（赋闲）");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币随从");
            map.put("正", "深度的专注和用心, 学术成就, 反映, 渴望有新的点子, 不顾实际的社会改良家, 携带讯息的人");
            map.put("逆", "不切实际的人,无法承认显而易见的事实,想法的纷乱,不合逻辑的思考,反叛,挥霍浪费,不利的消息");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币十");
            map.put("正", "富有,安全,家务事,血统,继承,家");
            map.put("逆", "差的机运, 可能的损失, 冒险犯难, 抢劫, 失去继承权, 放荡, 赌博");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币九");
            map.put("正", "成就, 洞察力, 辨别, 周到, 远见（先见之明）,谨慎, 物质上的富足, 对大自然之爱");
            map.put("逆", "捣蛋恶搞,危险,风暴,意图不轨,或许会失去一位好友或珍贵的财物");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币八");
            map.put("正", "学徒,技术艺能,学习快速,坦率,谦逊,手工艺品,个人的努力");
            map.put("逆", "缺乏雄心壮志, 虚荣, 自大自满, 醒悟（理想破灭）,高利贷, 伪善, 阿谀谄媚, 密谋策略");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币七");
            map.put("正", "心灵手巧, 成长, 努力工作, 进步, 成功的交易, 金钱, 宝物");
            map.put("逆", "无耐性,局促不安,不谨慎的行动,金钱的损失,不明智的投资");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币六");
            map.put("正", "慷慨,慈善行为,施舍,善良,报酬,礼物,物质上的获得");
            map.put("逆", "贪婪, 自私自利, 羡慕, 嫉妒, 呆帐, 负债");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币五");
            map.put("正", "穷困, 损失, 失败, 错误, 情妇, 爱人");
            map.put("逆", "坏倾向的逆转,事务上的新兴趣,克服倾覆,婚姻或爱情关系不和谐");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币四");
            map.put("正", "对物质财富的喜爱,囤积者,放高利贷者,吝啬鬼,无法与人分享");
            map.put("逆", "物质事务方面的挫败, 反对进一步获得, 疑虑和耽搁, 挥霍无度的人");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币三");
            map.put("正", "极强的商业或工作技能, 优异, 完美, 美术才能, 尊严, 名声, 层级, 权力");
            map.put("逆", "情感脆弱,平庸,财务问题,司空见惯的意见,欠缺技巧,全神贯注");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币二");
            map.put("正", "执行新计画时的困境,困难的情况,新的麻烦,难堪");
            map.put("逆", "文学才能, 处事才干, 假装的快乐, 勉强的欢颜, 信件, 消息");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "金币ACE");
            map.put("正", "完美, 达成, 富庶, 幸福, 至喜, 黄金, 有价值的硬币或人工制品, 宝物, 物质上和精神上满足的结合");
            map.put("逆", "富庶但不快乐,不当使用财富,浪费的金钱,为金钱而堕落,吝啬,贪心,愚人金");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯国王");
            map.put("正", "责任感和创意,有学养的人,专业的,商人,律师,虔诚的人,科学家,体贴周到的人,善良而可靠,开明宽大的作为,艺术家,对科学和艺术感兴趣,慷慨大方");
            map.put("逆", "艺术家性格, 双面的交易, 丑闻, 损失, 毁灭, 不公正, 诡诈而无美德可言的人");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯王后");
            map.put("正", "热心和公正的人, 被锺爱和敬重, 好朋友和好母亲, 奉献的妻子, 实际, 正直, 爱心和智慧, 远见的天赋");
            map.put("逆", "不正直,不可信赖,恶行");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯骑士");
            map.put("正", "即将面临一个邀请或机会,抵达,接近,前进,吸引,劝诱,恳求,挑战,提案");
            map.put("逆", "暗中, 手段, 欺骗, 狡猾奸诈的人, 有诈骗能力的人");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯随从");
            map.put("正", "勤勉好学和专心致志的人, 反映的, 凝思默想, 忠诚, 自动自发地提供服务朝向特定目标努力, 有助益的人, 值得信赖的工作人员");
            map.put("逆", "倾斜,偏差越轨,脆弱易感的,暂时的纷乱焦躁,引诱,阿谀谄媚者");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯十");
            map.put("正", "家庭,欢喜,愉悦,和平,爱情,满足,美好的家庭,荣誉,尊重,美德");
            map.put("逆", "失去友谊, 不快乐, 家庭纷争, 琐碎, 盛怒, 意见的差异");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯九");
            map.put("正", "成功, 物质上的成就, 利益, 安宁, 富足安康, 丰富充沛, 健康良好, 克服困难");
            map.put("逆", "错误,物质上的损失,不完美,不当、错误的信任,虚假的自由,对立,差异");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯八");
            map.put("正", "中止努力,失望,放弃先前的计画,羞怯,谦逊,扬弃成功");
            map.put("逆", "努力不懈直到完全成功, 节庆, 兴高采烈");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯七");
            map.put("正", "幻想, 想像力, 白日梦, 愚蠢的念头, 充满渴望的想法, 虚幻的成功");
            map.put("逆", "欲望,决心,强大的意志力,接近达成的目标,明智的选择");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯六");
            map.put("正", "回忆,过往的影响,已经消逝的事物,飞逝的童年,乡愁,褪色的印象");
            map.put("逆", "未来, 在前端的机会, 将来的事件, 新的展望, 计画可能失败, 即将来临的");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯五");
            map.put("正", "部分损失, 后悔, 没有真正意义的友谊, 没有真正爱情的婚姻, 继承, 不完整的合结合或合夥关系");
            map.put("逆", "充满希望的远景,美好的期许,新的联盟,亲近关系,老友归来,复合、重聚");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯四");
            map.put("正", "疲倦,反感,厌恶,失望,难堪的经验,人生中的停摆阶段");
            map.put("逆", "新的可能性, 新的人际关系, 对旧问题的新见解, 新知识");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯三");
            map.put("正", "问题的解决, 结论, 安慰, 治疗, 满意的结果, 妥协共识");
            map.put("逆", "过渡享乐,过剩,奢侈品,失去声望,延迟,缺乏感恩");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯二");
            map.put("正", "爱情,新的友谊或是重修旧好,热情,结合,婚约,了解,合作,合夥关系,婚姻");
            map.put("逆", "不满足的爱, 虚假的友谊, 麻烦的关系, 离异, 分离, 交错的欲望, 对立, 误解");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "圣杯ACE");
            map.put("正", "充沛丰富, 满足, 完美, 欢喜, 丰腴, 财富, 丰饶多产, 美丽和愉悦, 满溢著良善, 美好的远景");
            map.put("逆", "转变,侵蚀,不安稳,贫乏,无回报的爱,不尽情的欢乐,虚假的心,矛盾不一致");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖国王");
            map.put("正", "正直而诚恳的人,成熟,智慧,忠实的,友善的,有同情心的,受过教育的,一位绅士,通常是已婚的人,父亲般的（慈祥）");
            map.put("逆", "严格, 苛刻, 稍嫌极端和夸张的想法, 墨守成规的, 深思熟虑的");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖王后");
            map.put("正", "富同情心和谅解的人, 友善的, 深情的, 纯洁贞节的, 实际的, 魅力和优雅, 亲切的女主人, 真诚地对他人感到兴趣关切");
            map.put("逆", "妒忌,欺诈,可能不贞,起伏的情绪,善变,抵抗,对立");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖骑士");
            map.put("正", "一段旅程,前进到未知的领域,改变,飞航,缺乏,居住的变动");
            map.put("逆", "中断, 预料之外的转变, 争吵, 人际关系的破裂, 决裂");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖随从");
            map.put("正", "忠诚和忠心的人, 一位使者, 间谍密探, 能信任的朋友, 意图良好的陌生人, 始终如一的人, 携带重要讯息的人");
            map.put("逆", "优柔寡断,不情愿,不安善变,爱嚼舌根的人（很八卦的人）,携带著坏音讯的人,会使你伤心的人,不愉悦");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖十");
            map.put("正", "过重的压力,问题马上能解决,奋力达成目标或是维持一定的水准或地位,或许会把力量用在自私的目标上");
            map.put("逆", "困难, 阴谋策略, 叛徒, 骗徒, 推托藉口, 会有一些损失");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖九");
            map.put("正", "困难或变动的预料, 期待, 隐藏的敌人, 欺骗, 纪律, 顺序, 当下的挣扎间歇了");
            map.put("逆", "障碍,逆境,问题,延迟,灾难,须克服的阻碍,不健康");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖八");
            map.put("正", "迅速的行动,突然的进展或动作,速度,匆促地下决定,过於快速的进展");
            map.put("逆", "争论之刺（恼怒之处）,嫉妒, 困扰, 停滞不前, 家庭的争执");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖七");
            map.put("正", "成功, 获得, 打破阴霾险境, 优越, 胜利");
            map.put("逆", "惊愕,焦虑,难堪,迟疑导致损失,困惑茫然");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖六");
            map.put("正", "征服,胜利,好消息,进展,期待,付出努力而达成心愿");
            map.put("逆", "无限期的延迟, 恐惧, 挂念, 不忠诚, 表面上的利益, 不确定的获得");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖五");
            map.put("正", "不满足的欲望, 挣扎, 劳力, 暴力的争斗, 障碍");
            map.put("逆", "诡计,自相矛盾,错综复杂,牵累,切莫迟疑");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖四");
            map.put("正", "罗曼史,社会,和谐,新获得的兴盛,平静安宁,努力的果实,挣扎后的喘息");
            map.put("逆", "失去安稳平静, 未完成的恋情, 局促不安, 被消磨的美丽, 不完整的快乐");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖三");
            map.put("正", "实际的知识, 商业敏锐度, 事业, 协商谈判, 交易, 商业");
            map.put("逆", "别有用心的援助,减轻不幸,当心所提供的帮助");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖二");
            map.put("正", "成熟的个体,管理者,达到目标和需要,无畏无惧,负责尽职的勇气,支配型的人格");
            map.put("逆", "伤心, 麻烦, 起因於他人的阻碍, 失去信心, 惊讶");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "权杖ACE");
            map.put("正", "创造, 起始发源, 发明, 一件事的开始, 幸运, 事业, 获得, 继承, 小孩出生, 一段具有意义的经验开始了, 一段冒险, 恶搞");
            map.put("逆", "错误的开始,乌云密布的前景,未实现的目标,衰微,空虚的存在,苦恼,计画的取消");

            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑国王");
            map.put("正", "富有行动力和决断力的人,有经验的,有权威的,控制掌握的,指挥若定（威风、居高临下）,专业人士,具高度分析能力的人,正直,力量,优越");
            map.put("逆", "赶尽杀绝的人, 残酷, 冲突, 自私, 虐待狂, 引发不必要的动乱和悲哀的人, 倔强刚愎");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑皇后");
            map.put("正", "机智的, 极为敏感的, 难以捉摸的人, 或许代表寡妇或是悲伤的女人, 哀悼, 贫困, 缺乏, 孤独, 分离, 曾经有著极度快乐的人但现在体验到了不幸和逆转的焦虑");
            map.put("逆", "心胸狭窄,有恶意的,顽固,欺诈,报复,过於拘谨,险恶的敌人,坏脾气的人");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑骑士");
            map.put("正", "勇气,技巧,能力,年轻人的力量与冲撞,英雄行径,对抗与战争,无畏的急骋冲进未知当中,深谙行动和战事的专家");
            map.put("逆", "无能, 轻率, 为一个女人争吵或毁灭, 冲动造成的错误, 自吹自擂的呆子, 单纯的");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑随从");
            map.put("正", "夙夜匪懈, 敏捷, 刺探, 审慎低调的人, 活力旺盛的年轻人, 柔软的身躯, 这张牌象徵一个人善於察觉、分辨和揭露未知或更不明显的事物, 洞察力");
            map.put("逆", "被发现的冒充者,生病也是有可能的,无力面对更强大的力量,缺乏准备");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑十");
            map.put("正", "毁灭,苦痛,折磨,精神上的痛苦,荒芜寂寞,不幸,失望");
            map.put("逆", "受惠, 利益, 暂时的拥有, 改善, 刹时的成功, 瞬间的优势");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑九");
            map.put("正", "关心, 流产, 为所爱的人担心, 绝望");
            map.put("逆", "怀疑,中伤的流言,耻辱,顾忌,羞怯,阴暗的特质,有理由的恐惧");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑八");
            map.put("正", "紧要关头,冲突,控制,禁锢,骚动,坏消息,批评,生病,诽谤");
            map.put("逆", "过往的背叛, 困难, 辛勤工作, 消沈, 忧虑不安, 意外, 事故");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑七");
            map.put("正", "新计画, 心愿, 坚忍不拔, 毅力, 尽力而为, 希望, 信心, 幻想, 局部的成功");
            map.put("逆", "争执,不明确的建议和劝告,慎重的,中伤,胡言乱语");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑六");
            map.put("正", "旅途和行程,固执的企图克服困难,权宜之行为,焦虑之后的成功");
            map.put("逆", "僵局, 无用的提案, 表白, 宣布");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑五");
            map.put("正", "征服, 击败, 破怀他人, 退化, 敌手可能兴起了, 废止、撤回, 恶名, 不名誉");
            map.put("逆", "不确定的展望,损失或是击败的机会,虚弱,或许有不幸会降临你的朋友,魅惑,埋葬");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑四");
            map.put("正", "延缓,病后的休养,歇息,补充,孤单,放逐,撤退,放弃");
            map.put("逆", "活动, 慎重, 警觉, 经济, 小心的前进, 希望重获所失去的");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑三");
            map.put("正", "缺乏, 悲伤, 失望, 争吵, 调动, 消散, 转移注意, 对立, 分离, 延迟");
            map.put("逆", "注意力散乱,困惑,混乱,不和,错误,误解,不融洽,焦虑,损失,疏远");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑二");
            map.put("正", "均衡的力量,和谐,坚定刚毅,协定（和谐）,抵销的要素,僵局,锺爱");
            map.put("逆", "口是心非, 不实或扭曲的陈述, 不名誉, 背叛变节, 虚伪的朋友, 谎言");
            tarots.add(map);
            map = new HashMap<String, String>();
            map.put("排", "宝剑ACE");
            map.put("正", "强大的决心, 开创, 力量, 力气, 行动, 极度, 胜利, 权力, 成功, 丰腴, 兴旺, 深层的情感, 爱情, 优胜, 征服");
            map.put("逆", "灾害,专制暴政,不幸,自我毁灭,火爆的脾气,难堪,障碍,贫瘠");
        }
    }
}
