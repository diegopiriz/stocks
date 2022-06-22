package com.diego.demo;

public class Stocks {
    public static final String[] revolut = {
            "FOXA", "ME", "TWOU", "MMM", "ABT", "ABBV", "ANF", "ACN", "ACEV", "ATVI", "ADBE", "AAP", "AMD", "AFRM",
            "AFL", "A", "AGL", "AGNC", "AIG", "AL", "APD", "ABNB", "AJAX", "AKAM", "ALB", "AA", "BABA", "ALIT", "ALGN",
            "ALLT", "MDRX", "ALL", "ALLY", "GOOGL", "GOOG", "AYX", "ATUS", "MO", "AMZN", "ABEV", "AMC", "AMRC", "AAL",
            "AXL", "AEO", "AEP", "AXP", "AMH", "AMT", "COLD", "AME", "AMGN", "FOLD", "AMRX", "APH", "ADI", "PLAN",
            "ANGI", "AU", "BUD", "NLY", "ANSS", "AM", "AR", "ANTM", "AOS", "APA", "ARI", "APSG", "APPN", "AAPL", "AMAT",
            "APP", "ATR", "ARMK", "ADM", "ARNC", "ARCT", "ARCC", "ANET", "ARR", "ARVL", "ASAN", "ASML", "AZN", "T",
            "ATIP", "AY", "ACIC", "TEAM", "ADSK", "ATHM", "ADP", "AZO", "AVLR", "AVB", "AVPT", "EQH", "AXTA", "AXON",
            "BTG", "BIDU", "BKR", "BLL", "BLDP", "BBD", "BMA", "BSBR", "BAC", "BZUN", "BCS", "GOLD", "BHC", "BAX",
            "BBAR", "BDX", "BDC", "BRK.B", "BBY", "BYND", "BHP", "BILI", "BIO", "BIIB", "BMRN", "BNTX", "BJ", "BB",
            "BLK", "BX", "BK", "BA", "BKNG", "BAH", "BWA", "BSX", "BOX", "BP", "BRFS", "BMY", "BRX", "AVGO", "BAM",
            "BAMR", "BIPC", "BIP", "BEPC", "BEP", "BF.B", "BTWN", "BMBL", "BG", "BTAQ", "AI", "CABO", "COG", "CDNS",
            "CZR", "CSIQ", "CANO", "CAJ", "GOEV", "CGC", "COF", "CAH", "CG", "CCL", "CARR", "CARS", "CVNA", "CAT",
            "CBOE", "CBRE", "PRPB", "CX", "CVE", "CNC", "CNP", "CERN", "CF", "CHPT", "SCHW", "CHTR", "CHKP", "CHGG",
            "SQM", "CC", "LNG", "CHKAQ", "CVX", "CHWY", "CHS", "CIM", "CHL", "ZNH", "CMG", "CIEN", "CI", "XEC", "CTAS",
            "CSCO", "C", "CFG", "CTXS", "CLNE", "CWEN", "CLF", "CLDR", "NET", "CLVS", "CME", "CNX", "KO", "CDE", "CGNX",
            "CTSH", "CRHC", "COIN", "CL", "CMCSA", "CMA", "COMM", "CYH", "SBS", "CIG", "SID", "BVN", "COMP", "CAG",
            "CNDT", "COP", "ED", "STZ", "WISH", "CLR", "CONX", "GLW", "CRSR", "CTVA", "CSGP", "COST", "COTY", "CPNG",
            "COUR", "CVA", "BAP", "CRSP", "CROX", "CRWD", "CCI", "CSX", "CVS", "CYXT", "DAN", "DHR", "DQ", "DRI", "DAR",
            "DDOG", "MSP", "DVA", "DELL", "DAL", "DB", "DVN", "DXCM", "DEO", "FANG", "DIDI", "DLR", "APPS", "DBRG",
            "DIN", "DFS", "DISCA", "DISCK", "DISH", "DHC", "DOCU", "DG", "DLTR", "D", "DPZ", "DASH", "XPOA", "DHI",
            "DKNG", "DBX", "DTM", "DTE", "DUK", "DRE", "DUOL", "DD", "DXC", "ETAC", "EBAY", "ECL", "EIX", "EW", "ELAN",
            "EGO", "ELMS", "EA", "ESI", "LLY", "ERJ", "EMR", "OVV", "ET", "ENIA", "ENLC", "ENPH", "ETR", "EPD", "EOG",
            "EPAM", "EQT", "EFX", "EQIX", "EQNR", "ETRN", "EQD", "EQR", "EL", "ETSY", "EB", "EVR", "EXAS", "XGN",
            "EXEL", "EXC", "EXPE", "XOM", "FFIV", "FB", "FDS", "FAST", "FSLY", "FDX", "RACE", "FITB", "FEYE", "FHN",
            "AG", "FSLR", "FE", "FMAC", "FIS", "FISV", "FVE", "FVRR", "FLT", "FLEX", "FLR", "FL", "F", "FTNT", "FTV",
            "BEN", "FCX", "FREQ", "FSK", "FUBO", "FCEL", "FF", "GME", "GPS", "IT", "GDS", "GD", "GE", "GIS", "GM",
            "GNTX", "GNW", "GGB", "GILD", "GSK", "GNL", "GMED", "GDDY", "GOL", "GFI", "GS", "GT", "GPRO", "GPK", "GPRE",
            "GO", "GRPN", "GGAL", "BSMX", "SUPV", "TV", "GSAH", "GES", "GPORQ", "HRB", "HAL", "HBI", "HOG", "HMY", "HIG",
            "HAS", "HCA", "HDB", "HAAC", "HL", "HEI", "HSIC", "HLF", "HTZGQ", "HES", "HPE", "HLT", "HGV", "HIMX", "HFC",
            "HOLX", "HD", "HMC", "HON", "HRL", "HST", "HWM", "HPQ", "HSBC", "HTHT", "HUBS", "HUM", "HBAN", "HUN", "HCM",
            "HUYA", "H", "HYZN", "IAG", "IBM", "IBN", "IDXX", "IGMS", "ITW", "ILMN", "IMGN", "INCY", "INDI", "INFN",
            "INFY", "IIPR", "INO", "INTC", "IBKR", "ICPT", "ICE", "IHG", "IGT", "IP", "IPG", "INTU", "ISRG", "IVZ",
            "IVR", "ISBC", "NVTA", "INVH", "IQ", "IRBT", "IRM", "IS", "ITUB", "JBHT", "JNJ", "JKHY", "JBI", "JAZZ",
            "JD", "JEF", "JBLU", "FROG", "JKS", "DE", "JPM", "JMIA", "JNPR", "GRUB", "KAR", "KPLT", "K", "KDP", "KEY",
            "KEYS", "KMB", "KIM", "KMI", "KGC", "KKR", "KNX", "KSS", "KHC", "KTOS", "KR", "KT", "LB", "LRCX", "LVS",
            "LSCC", "LDOS", "LMND", "LEN", "LESL", "LEVI", "LXRX", "LX", "LPL", "LI", "FWONK", "LIN", "LYV", "LTHM",
            "LKQ", "LMT", "LOGI", "LOMA", "LOW", "LTC", "LCID", "LULU", "LUMN", "LYFT", "M", "MSGS", "MRO", "MPC",
            "MKL", "MQ", "MAR", "MMC", "MRVL", "MAS", "MA", "MTDR", "MTCH", "MAT", "MAXN", "MXIM", "MCFE", "MCD", "MUX",
            "MPW", "MDT", "MLCO", "MELI", "MRK", "MET", "MTD", "MFA", "MTG", "MGM", "MCHP", "MU", "MSFT", "MFG", "MBT",
            "MRNA", "MOMO", "MDLZ", "MGI", "MDB", "MNST", "MCO", "MS", "MORN", "MOS", "MSI", "MPLX", "MUFG", "MUR",
            "NDAQ", "NOV", "NTCO", "NAVI", "NKTR", "NPTN", "NTAP", "NTES", "NFLX", "NBIX", "NBEV", "EDU", "NRZ", "NYCB",
            "NYMT", "NYT", "NWL", "NEM", "NWSA", "NEE", "NICE", "NKE", "NKLA", "NIO", "NOAH", "NMR", "JWN", "NSC",
            "NTRS", "NOC", "NLOK", "NCLH", "NVS", "NVAX", "NVO", "NVCR", "NRG", "NUE", "NTNX", "NUVA", "NVDA", "NVR",
            "ORLY", "OTLY", "OXY", "ODP", "OKTA", "OLN", "OMC", "ON", "OKE", "OPK", "OPFI", "ORCL", "OGN", "ORGN",
            "ORA", "OSCR", "OTIS", "OSTK", "OZON", "PG", "PCAR", "PD", "PLTR", "PANW", "PAM", "PAAS", "PH", "PTEN",
            "PAYX", "PAYO", "PYPL", "PBF", "PDCE", "PTON", "PENN", "PBCT", "PEP", "PSTH", "PBR", "PFE", "PCG", "PM",
            "PSX", "PDD", "PINS", "PXD", "PBI", "PAA", "PLNT", "PLUG", "PNC", "POSH", "PPG", "PPL", "PBH", "PRI", "PGR",
            "PLD", "PFPT", "PTRA", "PRU", "PEG", "PSA", "PHM", "PSTG", "QTWO", "QCOM", "QLYS", "PWR", "QD", "QRTEA",
            "RXT", "RL", "RRC", "RTX", "RLGY", "REAL", "O", "RDFN", "REGN", "RF", "RTP", "REGI", "RSG", "RMD", "QSR",
            "RH", "RNG", "RIO", "RAD", "HOOD", "RBLX", "RKT", "ROK", "ROKU", "ROOT", "ROST", "RY", "RCL", "RDS.A",
            "RES", "SPGI", "CRM", "SBH", "SGMO", "SNY", "SLB", "SE", "SEB", "STX", "SMFR", "SRE", "NOW", "SHAK",
            "SHW", "SHOP", "SBSW", "SPG", "SIRI", "SKX", "SKIL", "SWKS", "SLM", "SM", "SMAR", "SNAP", "SNOW", "IPOD",
            "IPOF", "SOFI", "SOGO", "SEDG", "SONY", "SO", "SCCO", "LUV", "SWN", "SAVE", "SPLK", "SPOT", "SFM", "SQ",
            "SQSP", "SSNC", "SRAC", "SWK", "SBUX", "STT", "STLD", "STLA", "SFIX", "STNE", "SYK", "SMFG", "SUMO", "SU",
            "NOVA", "SPWR", "RUN", "SSSS", "SYF", "SNPS", "SYY", "TMUS", "TROW", "TTWO", "TSM", "TAK", "TAL", "TALK",
            "SKT", "TPR", "TRGP", "TGT", "TTM", "FTI", "TECK", "TDOC", "TFX", "VIV", "TPX", "TME", "TER", "TSLA",
            "TEVA", "TXN", "AES", "CLX", "DOW", "GEO", "BARK", "SMG", "TMO", "TLRY", "TIMB", "TJX", "TD", "TTE", "TM",
            "TPGY", "PACE", "TPIC", "TTD", "TW", "TRXC", "TRV", "TCOM", "TRIP", "TGI", "TFC", "TSP", "TWCT", "TWLO",
            "TWTR", "TWO", "TSN", "UBER", "UBS", "PATH", "ULTA", "UCTT", "UGP", "UAA", "UA", "UNP", "UAL", "UMC", "X",
            "UNH", "UNIT", "U", "UNM", "TIGR", "UPS", "URBN", "USB", "SLCA", "VALE", "VLO", "VACQ", "VGR", "VEEV",
            "VTR", "VER", "VRSN", "VRSK", "VZ", "VRTX", "VFC", "VIACA", "VTRS", "VICI", "VIPS", "VIR", "SPCE", "V",
            "VSCO", "VST", "VMW", "VG", "VIH", "VRM", "WAB", "WBA", "WMT", "DIS", "WCN", "WM", "W", "WB", "WFC", "WELL",
            "WEN", "WDC", "WU", "WRK", "WEX", "WY", "WTM", "WMB", "WING", "WIT", "WIX", "WDAY", "WKHS", "WWE", "WYNN",
            "XEL", "XRX", "XLNX", "XPEV", "AUY", "YETI", "YPF", "YUM", "YUMC", "YY", "ZBRA", "ZEN", "Z", "ZBH", "ZION",
            "ZIP", "ZTS", "ZM", "ZS", "ZTO", "ZNGA",
    };

    public static final String[] watch = {
            "TSLA", "AAPL", "AMZN", "MSFT", "GOOGL", "DIS", "NFLX", "FB", "BYND", "MA", "V",
            "UBER", "NVDA", "MCD", "BKNG", "BAC", "NIO", "GOOG", "INTC", "SBUX", "SHOP", "WORK", "NIKE", "AXP",
            "WFC", "SNAP", "QCOM", "PYPL", "PINS", "GOL", "PRP", "SPOT", "CRM", "TRIP",
            "EA", "EBAY", "DOCU", "NET", "DBX", "DELL", "DDOG", "DPZ", "ADSK", "CLDR", "NDAQ", "MELI",
            "USB", "TCOM", "NMW", "GDDY"
    };

    public static final String[] techGiants = {
            "APPL", "MSFT", "GOOG", "AMZN", "FB", "BABA", "0700.HK", "SMSN.L", "INTC", "CMCSA", "CSCO", "ORCL",
            "SAP.DE", "ADBE", "CRM", "NVDA", "NFLX", "ACN", "AVGO", "IBM", "TXN", "CHTR", "QCOM", "SONY",
            "TSLA"
    };

    public static final String[] marijuana = {
            "SNDL", "ACB", "TGODF", "TLRY", "MRMD", "MCIG", "TRTC", "HEXO", "MJNA", "CRON", "CGC", "APHA", "OGI",
            "GRGW", "MJ", "GTBIF", "ZYNE", "TCNNF", "YOLO", "HRVSF", "CURLF", "VFF", "CRLBF", "IIPR", "GWPH", "STZ",
            "CANN", "ARNA", "CRBP", "CVSI", "GRWC", "LBUY", "ACRGF", "CHOOF"
    };

    public static final String[] revolutPopular = {
            "TSLA", "AAPL", "AAL", "AMZN", "NIO", "MSFT", "AMD", "BA", "NFLX", "DAL", "KO", "GOOGL", "FB", "DIS",
            "SPCE", "NCLH", "BABA", "NVDA", "ZM", "CCL", "BYND", "INTC", "IVR", "F", "XOM", "T", "SHOP",
            "UBER", "MA", "V", "RCL", "GE", "PFE", "ATVI", "MCD", "GRPN", "ABEV", "GILD", "BAC", "AMRX", "GPRO", "SONY",
            "BKNG", "MRO", "MMM", "ADBE", "SBUX", "SQ", "JMIA"
    };
}
