package web.application.com.common.uitls;

/**
 * 类功能说明：常量工具类
 */
public final class Constants {

	/**
     * 此类不需要实例化
     */
	private Constants() {
		
	}

	/**
	 * 状态说明
	 */
	public interface VARCHAR {
		public final static String ZERO = "0";
		public final static String ONE = "1";
		public final static String TWO = "2";
		public final static String THREE = "3";
	}

	/**
	 * 数字说明
	 */
	public interface NUMBER {
		public final static int ZERO = 0;
		public final static int ONE = 1;
		public final static int TWO = 2;
		public final static int THREE = 3;
	}
	
	/**
	 * json数据状态码
	 */
	public interface result {
		public final static String ERR_CODE = "errCode";
		public final static String ERR_MSG = "errMsg";
		public final static String RESULT_DATA = "resultData";
		public final static String LIST = "list";
		public final static String PAGE_CURRENT = "pageCurrent";
		public final static String PAGE_SIZE = "pageSize";
		public final static String TOTAL_COUNT = "totalCount";
		public final static String TOTAL_PAGE = "totalPage";
	}

}
