package ${packName}.constant.${entity?lower_case};

/**
 * ${explain}常量
 */
public class ${entity?cap_first}Constant {
    /**
     * 正常状态
     */
    private static final int normal = 0;
    /**
     * 删除状态
     */
    private static final int delete = 1;

    /**
     * 页面标题
     */
    public static final String ADD_PAGE_TITLE = "添加${explain}";
    public static final String UPDATE_PAGE_TITLE = "修改${explain}";
    public static final String LIST_PAGE_TITLE = "${explain}列表";

    /**
     * 功能操作提示信息
     */
    public static final String YES="操作成功";
    public static final String NO="操作失败";
}
