<template>
    <div class="main-content">

        <!-- 条件查询 -->
        <div v-if="showFlag">
            <el-form :inline="true" :model="searchForm" class="form-content">
                <el-row :gutter="20" class="slt" :style="{justifyContent:contents.searchBoxPosition=='1'?'flex-start':contents.searchBoxPosition=='2'?'center':'flex-end'}">
                                 
                     <el-form-item :label="contents.inputTitle == 1 ? '众筹编号' : ''">
                         <el-input prefix-icon="el-icon-search" v-model="searchForm.zhongchouUuidNumber" placeholder="众筹编号" clearable></el-input>
                     </el-form-item>
         
                     <el-form-item :label="contents.inputTitle == 1 ? '众筹名称' : ''">
                         <el-input prefix-icon="el-icon-search" v-model="searchForm.zhongchouName" placeholder="众筹名称" clearable></el-input>
                     </el-form-item>
         
                     <el-form-item :label="contents.inputTitle == 1 ? '众筹类型' : ''">
                        <el-select v-model="searchForm.zhongchouTypes" placeholder="请选择众筹类型">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                               v-for="(item,index) in zhongchouTypesSelectSearch"
                               v-bind:key="index"
                               :label="item.indexName"
                               :value="item.codeIndex">
                            <!--lable是要展示的名称-->
                            <!--value是值-->
                            </el-option>
                        </el-select>
                     </el-form-item>

                                         
                     <el-form-item :label="contents.inputTitle == 1 ? '众筹状态' : ''">
                        <el-select v-model="searchForm.zhongchouZhuangtaiTypes" placeholder="请选择众筹状态">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                               v-for="(item,index) in zhongchouZhuangtaiTypesSelectSearch"
                               v-bind:key="index"
                               :label="item.indexName"
                               :value="item.codeIndex">
                            <!--lable是要展示的名称-->
                            <!--value是值-->
                            </el-option>
                        </el-select>
                     </el-form-item>

                                                 
                     <el-form-item :label="contents.inputTitle == 1 ? '快递状态' : ''">
                        <el-select v-model="searchForm.zhongchouKuaidiTypes" placeholder="请选择快递状态">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                               v-for="(item,index) in zhongchouKuaidiTypesSelectSearch"
                               v-bind:key="index"
                               :label="item.indexName"
                               :value="item.codeIndex">
                            <!--lable是要展示的名称-->
                            <!--value是值-->
                            </el-option>
                        </el-select>
                     </el-form-item>

                                        
                                         
                    <el-form-item :label="contents.inputTitle == 1 ? '商品编号' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.shangpinUuidNumber" placeholder="商品编号" clearable></el-input>
                    </el-form-item>
                     
                    <el-form-item :label="contents.inputTitle == 1 ? '商品名称' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.shangpinName" placeholder="商品名称" clearable></el-input>
                    </el-form-item>
                                         
                    <el-form-item :label="contents.inputTitle == 1 ? '商品类型' : ''">
                        <el-select v-model="searchForm.shangpinTypes" placeholder="请选择商品类型">
                            <el-option label="=-请选择-=" value=""></el-option>
                            <el-option
                                    v-for="(item,index) in shangpinTypesSelectSearch"
                                    v-bind:key="index"
                                    :label="item.indexName"
                                    :value="item.codeIndex">
                                <!--lable是要展示的名称-->
                                <!--value是值-->
                            </el-option>
                        </el-select>
                    </el-form-item>
                                                                                                                                                                 
                    <el-form-item :label="contents.inputTitle == 1 ? '用户姓名' : ''">
                        <el-input prefix-icon="el-icon-search" v-model="searchForm.yonghuName" placeholder="用户姓名" clearable></el-input>
                    </el-form-item>
                                                                                                                        

                    <el-form-item>
                        <el-button type="success" @click="search()">查询<i class="el-icon-search el-icon--right"/></el-button>
                    </el-form-item>
                </el-row>
                <el-row class="ad" :style="{justifyContent:contents.btnAdAllBoxPosition=='1'?'flex-start':contents.btnAdAllBoxPosition=='2'?'center':'flex-end'}">
                    <el-form-item>
                        <el-button
                                v-if="isAuth('zhongchou','新增') && sessionTable == 'users'"
                                type="success"
                                icon="el-icon-plus"
                                @click="addOrUpdateHandler()"
                        >新增</el-button>
                        <el-button
                                v-if="isAuth('zhongchou','新增') && sessionTable == 'yonghu'"
                                type="success"
                                icon="el-icon-plus"
                                @click="addOrUpdateHandler()"
                        >发起众筹</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('zhongchou','删除')"
                                :disabled="dataListSelections.length <= 0"
                                type="danger"
                                icon="el-icon-delete"
                                @click="deleteHandler()"
                        >删除</el-button>
                        &nbsp;
                        <el-button
                                v-if="isAuth('zhongchou','报表')"
                                type="success"
                                icon="el-icon-pie-chart"
                                @click="chartDialog()"
                        >报表</el-button>
                        &nbsp;
                        <a style="text-decoration:none" class="el-button el-button--success"
                           v-if="isAuth('zhongchou','导入导出')"
                           icon="el-icon-download"
                           href="http://localhost:8080/shanchouwang/upload/zhongchouMuBan.xls"
                        >批量导入众筹数据模板</a>
                        &nbsp;
                        <el-upload
                                v-if="isAuth('zhongchou','导入导出')"
                                style="display: inline-block"
                                action="shanchouwang/file/upload"
                                :on-success="zhongchouUploadSuccess"
                                :on-error="zhongchouUploadError"
                                :show-file-list = false>
                            <el-button
                                    v-if="isAuth('zhongchou','导入导出')"
                                    type="success"
                                    icon="el-icon-upload2"
                            >批量导入众筹数据</el-button>
                        </el-upload>
                        &nbsp;
                        <!-- 导出excel -->
                        <download-excel v-if="isAuth('zhongchou','导入导出')" style="display: inline-block" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "zhongchou.xls">
                            <!-- 导出excel -->
                            <el-button
                                    type="success"
                                    icon="el-icon-download"
                            >导出</el-button>
                        </download-excel>
                        &nbsp;
                    </el-form-item>
                </el-row>
            </el-form>
            <div class="table-content">
                <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
                          :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle"
                          :border="contents.tableBorder"
                          :fit="contents.tableFit"
                          :stripe="contents.tableStripe"
                          :row-style="rowStyle"
                          :cell-style="cellStyle"
                          :style="{width: '100%',fontSize:contents.tableContentFontSize,color:contents.tableContentFontColor}"
                          v-if="isAuth('zhongchou','查看')"
                          :data="dataList"
                          v-loading="dataListLoading"
                          @selection-change="selectionChangeHandler">
                    <el-table-column  v-if="contents.tableSelection"
                                      type="selection"
                                      header-align="center"
                                      align="center"
                                      width="50">
                    </el-table-column>
                    <el-table-column label="索引" v-if="contents.tableIndex" type="index" width="50" />
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="shangpinUuidNumber"
                                      header-align="center"
                                      label="商品编号">
                        <template slot-scope="scope">
                            {{scope.row.shangpinUuidNumber}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="shangpinName"
                                      header-align="center"
                                      label="商品名称">
                        <template slot-scope="scope">
                            {{scope.row.shangpinName}}
                        </template>
                    </el-table-column>
                    <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="shangpinPhoto"
                               header-align="center"
                               width="200"
                               label="商品照片">
                        <template slot-scope="scope">
                            <div v-if="scope.row.shangpinPhoto">
                                <img :src="scope.row.shangpinPhoto" width="100" height="100">
                            </div>
                            <div v-else>无图片</div>
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="shangpinTypes"
                                      header-align="center"
                                      label="商品类型">
                        <template slot-scope="scope">
                            {{scope.row.shangpinValue}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="shangpinNewMoney"
                                      header-align="center"
                                      label="现价">
                        <template slot-scope="scope">
                            {{scope.row.shangpinNewMoney}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="yonghuName"
                                      header-align="center"
                                      label="用户姓名">
                        <template slot-scope="scope">
                            {{scope.row.yonghuName}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="yonghuPhone"
                                      header-align="center"
                                      label="用户手机号">
                        <template slot-scope="scope">
                            {{scope.row.yonghuPhone}}
                        </template>
                    </el-table-column>
                    <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="yonghuPhoto"
                               header-align="center"
                               width="200"
                               label="用户头像">
                        <template slot-scope="scope">
                            <div v-if="scope.row.yonghuPhoto">
                                <img :src="scope.row.yonghuPhoto" width="100" height="100">
                            </div>
                            <div v-else>无图片</div>
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouUuidNumber"
                                   header-align="center"
                                   label="众筹编号">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouUuidNumber}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouName"
                                   header-align="center"
                                   label="众筹名称">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouName}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="zhongchouTypes"
                                      header-align="center"
                                      label="众筹类型">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouValue}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="zhongchouKucunNumber"
                                      header-align="center"
                                      label="众筹数量">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouKucunNumber}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="yichouMoney"
                                   header-align="center"
                                   label="已筹金额">
                        <template slot-scope="scope">
                            {{scope.row.yichouMoney}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouMoney"
                                   header-align="center"
                                   label="众筹总额">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouMoney}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="zhongchouClicknum"
                                      header-align="center"
                                      label="热度">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouClicknum}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="zhongchouZhuangtaiTypes"
                                      header-align="center"
                                      label="众筹状态">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouZhuangtaiValue}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouChenggongName"
                                   header-align="center"
                                   label="收件人姓名">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouChenggongName}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouChenggongPhone"
                                   header-align="center"
                                   label="收件人联系方式">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouChenggongPhone}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouChenggongAddress"
                                   header-align="center"
                                   label="收件人地址">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouChenggongAddress}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouChenggongKuaidiname"
                                   header-align="center"
                                   label="快递名称">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouChenggongKuaidiname}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="zhongchouChenggongKuaidiaddress"
                                   header-align="center"
                                   label="快递号">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouChenggongKuaidiaddress}}
                        </template>
                    </el-table-column>
                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                      prop="zhongchouKuaidiTypes"
                                      header-align="center"
                                      label="快递状态">
                        <template slot-scope="scope">
                            {{scope.row.zhongchouKuaidiValue}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="insertTime"
                                   header-align="center"
                                   label="发起时间">
                        <template slot-scope="scope">
                            {{scope.row.insertTime}}
                        </template>
                    </el-table-column>

                    <el-table-column  :sortable="contents.tableSortable" :align="contents.tableAlign"
                                   prop="jieshuTime"
                                   header-align="center"
                                   label="众筹截止时间">
                        <template slot-scope="scope">
                            {{scope.row.jieshuTime}}
                        </template>
                    </el-table-column>

                    <el-table-column width="300" :align="contents.tableAlign"
                                     header-align="center"
                                     label="操作">
                        <template slot-scope="scope">
                            <el-button v-if="isAuth('zhongchou','查看')" type="success" icon="el-icon-tickets" size="mini" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
                            <el-button v-if="isAuth('zhongchou','修改')" type="primary" icon="el-icon-edit" size="mini" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>

                            <el-button v-if="isAuth('zhongchou','删除')" type="danger" icon="el-icon-delete" size="mini" @click="deleteHandler(scope.row.id)">删除</el-button>
                            <!--<el-button v-if="isAuth('zhongchou','修改')" type="primary" icon="el-icon-add-location" size="mini" @click="plusZhongchouKucunNumber(scope.row.id,scope.row.zhongchouKucunNumber)">增加库存</el-button>-->
                            <!--<el-button v-if="isAuth('zhongchou','修改')" type="primary" icon="el-icon-delete-location" size="mini" @click="reduceZhongchouKucunNumber(scope.row.id,scope.row.zhongchouKucunNumber)">减少库存</el-button>-->

                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                        clsss="pages"
                        :layout="layouts"
                        @size-change="sizeChangeHandle"
                        @current-change="currentChangeHandle"
                        :current-page="pageIndex"
                        :page-sizes="[10, 20, 50, 100]"
                        :page-size="Number(contents.pageEachNum)"
                        :total="totalPage"
                        :small="contents.pageStyle"
                        class="pagination-content"
                        :background="contents.pageBtnBG"
                        :style="{textAlign:contents.pagePosition==1?'left':contents.pagePosition==2?'center':'right'}"
                ></el-pagination>
            </div>
        </div>
        <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
        <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

<!-- 库存 -->
		<el-dialog
                :title="zhongchouKucunNumberTitle"
                :visible.sync="zhongchouKucunNumberVisible"
                width="30%">
            <span>操作数量</span><el-input-number v-model="kucunNumber" :min="1" :max="100" label="描述文字"></el-input-number>
            <span slot="footer" class="dialog-footer">
			<el-button @click="zhongchouKucunNumberVisible = false">取 消</el-button>
			<el-button type="primary" @click="addZhongchouKucunNumber()">确 定</el-button>
		  </span>
        </el-dialog>

        <el-dialog title="统计报表" :visible.sync="chartVisiable" width="800">
            <el-date-picker
                    v-model="echartsDate"
                    type="year"
                    placeholder="选择年">
            </el-date-picker>
            <el-button @click="chartDialog()">查询</el-button>
            <div id="statistic" style="width:100%;height:600px;"></div>
            <span slot="footer" class="dialog-footer">
				<el-button @click="chartVisiable = false">关闭</el-button>
			</span>
        </el-dialog>

    </div>
</template>
<script>
    import AddOrUpdate from "./add-or-update";
    import styleJs from "../../../utils/style.js";
    import utilsJs from "../../../utils/utils.js";

    export default {
        data() {
        return {
            searchForm: {
                key: ""
            },
            sessionTable : "",//登录账户所在表名
            role : "",//权限
            userId:"",//当前登录人的id
    //级联表下拉框搜索条件
              shangpinTypesSelectSearch : [],
    //当前表下拉框搜索条件
              zhongchouTypesSelectSearch : [],
              zhongchouZhuangtaiTypesSelectSearch : [],
              zhongchouKuaidiTypesSelectSearch : [],
            form:{
                id : null,
                yonghuId : null,
                shangpinId : null,
                zhongchouUuidNumber : null,
                zhongchouName : null,
                zhongchouTypes : null,
                zhongchouKucunNumber : null,
                yichouMoney : null,
                zhongchouMoney : null,
                zhongchouClicknum : null,
                zhongchouZhuangtaiTypes : null,
                zhongchouChenggongName : null,
                zhongchouChenggongPhone : null,
                zhongchouChenggongAddress : null,
                zhongchouChenggongKuaidiname : null,
                zhongchouChenggongKuaidiaddress : null,
                zhongchouKuaidiTypes : null,
                zhongchouContent : null,
                insertTime : null,
                jieshuTime : null,
                zhongchouDelete : null,
                createTime : null,
            },
            dataList: [],
            pageIndex: 1,
            pageSize: 10,
            totalPage: 0,
            dataListLoading: false,
            dataListSelections: [],
            showFlag: true,
            sfshVisiable: false,
            shForm: {},
            chartVisiable: false,
            echartsDate: new Date(),//echarts的时间查询字段
            addOrUpdateFlag:false,
            contents:null,
            layouts: '',

            //导出excel
            json_fields: {
                //级联表字段
                     '商品编号': 'shangpinUuidNumber',
                     '商品名称': 'shangpinName',
                     '商品照片': 'shangpinPhoto',
                     '商品类型': 'shangpinTypes',
                     '商品原价': 'shangpinOldMoney',
                     '现价': 'shangpinNewMoney',
                     '是否上架': 'shangxiaTypes',
                     '用户姓名': 'yonghuName',
                     '用户手机号': 'yonghuPhone',
                     '用户身份证号': 'yonghuIdNumber',
                     '用户头像': 'yonghuPhoto',
                     '电子邮箱': 'yonghuEmail',
                     '余额': 'newMoney',
                //本表字段
                     '众筹编号': "zhongchouUuidNumber",
                     '众筹名称': "zhongchouName",
                     '众筹类型': "zhongchouTypes",
                     '众筹数量': "zhongchouKucunNumber",
                     '已筹金额': "yichouMoney",
                     '众筹总额': "zhongchouMoney",
                     '热度': "zhongchouClicknum",
                     '众筹状态': "zhongchouZhuangtaiTypes",
                     '收件人姓名': "zhongchouChenggongName",
                     '收件人联系方式': "zhongchouChenggongPhone",
                     '收件人地址': "zhongchouChenggongAddress",
                     '快递名称': "zhongchouChenggongKuaidiname",
                     '快递号': "zhongchouChenggongKuaidiaddress",
                     '快递状态': "zhongchouKuaidiTypes",
                     '发起时间': "insertTime",
                     '众筹截止时间': "jieshuTime",
            },

        //库存
            //操作数量
            kucunNumber: 1,
            //操作数据id
            kucunNumberId: null,
            //操作类型
            kucunNumberTypes: null,
            //原有数量
            zhongchouKucunNumber: 0,
            //标题
            zhongchouKucunNumberTitle: null,
            //模态框状态
            zhongchouKucunNumberVisible: false,
            };
        },
        created() {
            this.contents = styleJs.listStyle();
            this.init();
            this.getDataList();
            this.contentStyleChange()
        },
        mounted() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");

        },
        filters: {
            htmlfilter: function (val) {
                return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
            }
        },
        components: {
            AddOrUpdate,
        },
        computed: {
        },
        methods: {
            chartDialog() {
                let _this = this;
                let params = {
                    dateFormat :"%Y", //%Y-%m
                    riqi :_this.echartsDate.getFullYear(),
                    // riqi :_this.echartsDate.getFullYear()+"-"+(_this.echartsDate.getMonth() + 1 < 10 ? '0' + (_this.echartsDate.getMonth() + 1) : _this.echartsDate.getMonth() + 1),
                    thisTable : {//当前表
                        tableName :'zhongchou',//当前表表名,
                        sumColum : 'zhongchou_number', //求和字段
                        date : 'insert_time',//分组日期字段
                        // string : 'zhongchou_name',//分组字符串字段
                        // types : 'zhongchou_types',//分组下拉框字段
                    },
                    // joinTable : {//级联表（可以不存在）
                    //     tableName :'yonghu',//级联表表名
                    //     // date : 'insert_time',//分组日期字段
                    //     string : 'yonghu_name',//分组字符串字段
                    //     // types : 'yonghu_types',//分组下拉框字段
                    // }
                }
                _this.chartVisiable = true;
                _this.$nextTick(() => {
                    var statistic = this.$echarts.init(document.getElementById("statistic"), 'macarons');
                    this.$http({
                        url: "barSum",
                        method: "get",
                        params: params
                    }).then(({data}) => {
                        if(data && data.code === 0){

                            //柱状图 求和 已成功使用
                            //start
                            let yAxisName = "数值";//根据查询数据具体改(单列要改,多列不改)
                            let xAxisName = "月份";
                            let series = [];//具体数据值
                            data.data.yAxis.forEach(function (item,index) {
                                let tempMap = {};
                                // tempMap.name= ["数值"];//根据查询数据具体改(单列要改,多列不改)
                                tempMap.name=data.data.legend[index];
                                tempMap.type='bar';
                                tempMap.data=item;
                                series.push(tempMap);

                            })

                            var option = {
                                tooltip: {
                                    trigger: 'axis',
                                    axisPointer: {
                                        type: 'cross',
                                        crossStyle: {
                                            color: '#999'
                                        }
                                    }
                                },
                                toolbox: {
                                    feature: {
                                        // dataView: { show: true, readOnly: false },  // 数据查看
                                        magicType: { show: true, type: ['line', 'bar'] },//切换图形展示方式
                                        // restore: { show: true }, // 刷新
                                        saveAsImage: { show: true }//保存
                                    }
                                },
                                legend: {
                                    data: data.data.legend//标题  可以点击导致某一列数据消失
                                },
                                xAxis: [
                                    {
                                        type: 'category',
                                        name: xAxisName,
                                        data: data.data.xAxis,
                                        axisPointer: {
                                            type: 'shadow'
                                        }
                                    }
                                ],
                                yAxis: [
                                    {
                                        type: 'value',//不能改
                                        name: yAxisName,//y轴单位
                                        axisLabel: {
                                            formatter: '{value}' // 后缀
                                        }
                                    }
                                ],
                                series:series//具体数据
                            };
                            // 使用刚指定的配置项和数据显示图表。
                            statistic.setOption(option,true);
                            //根据窗口的大小变动图表
                            window.onresize = function () {
                                statistic.resize();
                            };
                            //end
                        }else {
                            this.$message({
                                message: "报表未查询到数据",
                                type: "success",
                                duration: 1500,
                                onClose: () => {
                                    this.search();
                                }
                            });
                        }
                    });
                });
                ////饼状图
                //_this.chartVisiable = true;
                // this.$nextTick(()=>{
                //     var statistic = this.$echarts.init(document.getElementById("statistic"),'macarons');
                //     let params = {
                //         tableName: "zhongchou",
                //         groupColumn: "zhongchou_types",
                //     }
                //     this.$http({
                //         url: "newSelectGroupCount",
                //         method: "get",
                //         params: params
                //     }).then(({data}) => {
                //         if (data && data.code === 0) {
                //             let res = data.data;
                //             let xAxis = [];
                //             let yAxis = [];
                //             let pArray = []
                //             var option = {};
                //             for(let i=0;i<res.length;i++){
                //                 xAxis.push(res[i].name);
                //                 yAxis.push(res[i].value);
                //                 pArray.push({
                //                     value: res[i].value,
                //                     name: res[i].name
                //                 })
                //                 option = {
                //                     title: {
                //                         text: '保险合同类型统计',
                //                         left: 'center'
                //                     },
                //                     tooltip: {
                //                         trigger: 'item',
                //                         formatter: '{b} : {c} ({d}%)'
                //                     },
                //                     series: [
                //                         {
                //                             type: 'pie',
                //                             radius: '55%',
                //                             center: ['50%', '60%'],
                //                             data: pArray,
                //                             emphasis: {
                //                                 itemStyle: {
                //                                     shadowBlur: 10,
                //                                     shadowOffsetX: 0,
                //                                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                //                                 }
                //                             }
                //                         }
                //                     ]
                //                 };
                //             }
                //                 statistic.setOption(option);
                //                 window.onresize = function() {
                //                     statistic.resize();
                //                 };
                //         }
                //     });
                // })
            },
            contentStyleChange() {
                this.contentSearchStyleChange()
                this.contentBtnAdAllStyleChange()
                this.contentSearchBtnStyleChange()
                this.contentTableBtnStyleChange()
                this.contentPageStyleChange()
            },
            contentSearchStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el => {
                        let textAlign = 'left'
                        if(this.contents.inputFontPosition == 2)
                            textAlign = 'center'
                            if (this.contents.inputFontPosition == 3) textAlign = 'right'
                                el.style.textAlign = textAlign
                            el.style.height = this.contents.inputHeight
                            el.style.lineHeight = this.contents.inputHeight
                            el.style.color = this.contents.inputFontColor
                            el.style.fontSize = this.contents.inputFontSize
                            el.style.borderWidth = this.contents.inputBorderWidth
                            el.style.borderStyle = this.contents.inputBorderStyle
                            el.style.borderColor = this.contents.inputBorderColor
                            el.style.borderRadius = this.contents.inputBorderRadius
                            el.style.backgroundColor = this.contents.inputBgColor
                    })
                    if (this.contents.inputTitle) {
                        document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el => {
                            el.style.color = this.contents.inputTitleColor
                            el.style.fontSize = this.contents.inputTitleSize
                            el.style.lineHeight = this.contents.inputHeight
                        })
                    }
                    setTimeout(() => {
                        document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el => {
                            el.style.color = this.contents.inputIconColor
                            el.style.lineHeight = this.contents.inputHeight
                        })
                        document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el => {
                            el.style.color = this.contents.inputIconColor
                            el.style.lineHeight = this.contents.inputHeight
                        })
                        document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el => {
                            el.style.lineHeight = this.contents.inputHeight
                        })
                    }, 10 )
                })
            },
            // 搜索按钮
            contentSearchBtnStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .slt .el-button--success').forEach(el => {
                        el.style.height = this.contents.searchBtnHeight
                        el.style.color = this.contents.searchBtnFontColor
                        el.style.fontSize = this.contents.searchBtnFontSize
                        el.style.borderWidth = this.contents.searchBtnBorderWidth
                        el.style.borderStyle = this.contents.searchBtnBorderStyle
                        el.style.borderColor = this.contents.searchBtnBorderColor
                        el.style.borderRadius = this.contents.searchBtnBorderRadius
                        el.style.backgroundColor = this.contents.searchBtnBgColor
                    })
                })
            },
            // 新增、批量删除
            contentBtnAdAllStyleChange() {
                this.$nextTick(() => {
                    document.querySelectorAll('.form-content .ad .el-button--success').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllAddFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllAddBgColor
                    })
                    document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllDelFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllDelBgColor
                    })
                    document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el => {
                        el.style.height = this.contents.btnAdAllHeight
                        el.style.color = this.contents.btnAdAllWarnFontColor
                        el.style.fontSize = this.contents.btnAdAllFontSize
                        el.style.borderWidth = this.contents.btnAdAllBorderWidth
                        el.style.borderStyle = this.contents.btnAdAllBorderStyle
                        el.style.borderColor = this.contents.btnAdAllBorderColor
                        el.style.borderRadius = this.contents.btnAdAllBorderRadius
                        el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
                    })
                })
            },
            // 表格
            rowStyle({row, rowIndex}) {
                if (rowIndex % 2 == 1) {
                    if (this.contents.tableStripe) {
                        return {color: this.contents.tableStripeFontColor}
                    }
                } else {
                    return ''
                }
            },
            cellStyle({row, rowIndex}) {
                if (rowIndex % 2 == 1) {
                    if (this.contents.tableStripe) {
                        return {backgroundColor: this.contents.tableStripeBgColor}
                    }
                } else {
                    return ''
                }
            },
            headerRowStyle({row, rowIndex}) {
                return {color: this.contents.tableHeaderFontColor}
            },
            headerCellStyle({row, rowIndex}) {
                return {backgroundColor: this.contents.tableHeaderBgColor}
            },
            // 表格按钮
            contentTableBtnStyleChange() {
                // this.$nextTick(()=>{
                //   setTimeout(()=>{
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--success').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnDetailFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnDetailBgColor
                //     })
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--primary').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnEditFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnEditBgColor
                //     })
                //     document.querySelectorAll('.table-content .tables .el-table__body .el-button--danger').forEach(el=>{
                //       el.style.height = this.contents.tableBtnHeight
                //       el.style.color = this.contents.tableBtnDelFontColor
                //       el.style.fontSize = this.contents.tableBtnFontSize
                //       el.style.borderWidth = this.contents.tableBtnBorderWidth
                //       el.style.borderStyle = this.contents.tableBtnBorderStyle
                //       el.style.borderColor = this.contents.tableBtnBorderColor
                //       el.style.borderRadius = this.contents.tableBtnBorderRadius
                //       el.style.backgroundColor = this.contents.tableBtnDelBgColor
                //     })

                //   }, 50)
                // })
            },
            // 分页
            contentPageStyleChange() {
                let arr = []
                if (this.contents.pageTotal) arr.push('total')
                if (this.contents.pageSizes) arr.push('sizes')
                if (this.contents.pagePrevNext) {
                    arr.push('prev')
                    if (this.contents.pagePager) arr.push('pager')
                    arr.push('next')
                }
                if (this.contents.pageJumper) arr.push('jumper')
                this.layouts = arr.join()
                this.contents.pageEachNum = 10
            },

            init() {
            },
            search() {
                this.pageIndex = 1;
                this.getDataList();
            },
            // 获取数据列表
            getDataList() {
                this.dataListLoading = true;
                let params = {
                    page: this.pageIndex,
                    limit: this.pageSize,
                    sort: 'id',
                }

                                         
                if (this.searchForm.shangpinUuidNumber!= '' && this.searchForm.shangpinUuidNumber!= undefined) {
                    params['shangpinUuidNumber'] = '%' + this.searchForm.shangpinUuidNumber + '%'
                }
                     
                if (this.searchForm.shangpinName!= '' && this.searchForm.shangpinName!= undefined) {
                    params['shangpinName'] = '%' + this.searchForm.shangpinName + '%'
                }
                                         
                if (this.searchForm.shangpinTypes!= '' && this.searchForm.shangpinTypes!= undefined) {
                    params['shangpinTypes'] = this.searchForm.shangpinTypes
                }
                                                                                                                                                                 
                if (this.searchForm.yonghuName!= '' && this.searchForm.yonghuName!= undefined) {
                    params['yonghuName'] = '%' + this.searchForm.yonghuName + '%'
                }
                                                                                                                                                         
                if (this.searchForm.zhongchouUuidNumber!= '' && this.searchForm.zhongchouUuidNumber!= undefined) {
                    params['zhongchouUuidNumber'] = '%' + this.searchForm.zhongchouUuidNumber + '%'
                }
         
                if (this.searchForm.zhongchouName!= '' && this.searchForm.zhongchouName!= undefined) {
                    params['zhongchouName'] = '%' + this.searchForm.zhongchouName + '%'
                }
         
                if (this.searchForm.zhongchouTypes!= '' && this.searchForm.zhongchouTypes!= undefined) {
                    params['zhongchouTypes'] = this.searchForm.zhongchouTypes
                }
                                         
                if (this.searchForm.zhongchouZhuangtaiTypes!= '' && this.searchForm.zhongchouZhuangtaiTypes!= undefined) {
                    params['zhongchouZhuangtaiTypes'] = this.searchForm.zhongchouZhuangtaiTypes
                }
                                                 
                if (this.searchForm.zhongchouKuaidiTypes!= '' && this.searchForm.zhongchouKuaidiTypes!= undefined) {
                    params['zhongchouKuaidiTypes'] = this.searchForm.zhongchouKuaidiTypes
                }
                                        
                params['zhongchouDelete'] = 1// 逻辑删除字段 1 未删除 2 删除


                this.$http({
                    url: "zhongchou/page",
                    method: "get",
                    params: params
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.dataList = data.data.list;
                        this.totalPage = data.data.total;
                    }else{
                        this.dataList = [];
                        this.totalPage = 0;
                    }
                    this.dataListLoading = false;
                });

                //查询级联表搜索条件所有列表
                this.$http({
                    url: "dictionary/page?dicCode=shangpin_types&page=1&limit=100",
                    method: "get",
                    page: 1,
                    limit: 100,
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.shangpinTypesSelectSearch = data.data.list;
                    }
                });
                //查询当前表搜索条件所有列表
                //填充下拉框选项
                this.$http({
                    url: "dictionary/page?dicCode=zhongchou_types&page=1&limit=100",
                    method: "get",
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.zhongchouTypesSelectSearch = data.data.list;
                    }
                });
                //填充下拉框选项
                this.$http({
                    url: "dictionary/page?dicCode=zhongchou_zhuangtai_types&page=1&limit=100",
                    method: "get",
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.zhongchouZhuangtaiTypesSelectSearch = data.data.list;
                    }
                });
                //填充下拉框选项
                this.$http({
                    url: "dictionary/page?dicCode=zhongchou_kuaidi_types&page=1&limit=100",
                    method: "get",
                }).then(({data}) => {
                    if(data && data.code === 0){
                        this.zhongchouKuaidiTypesSelectSearch = data.data.list;
                    }
                });
            },
            //每页数
            sizeChangeHandle(val) {
                this.pageSize = val;
                this.pageIndex = 1;
                this.getDataList();
            },
            // 当前页
            currentChangeHandle(val) {
                this.pageIndex = val;
                this.getDataList();
            },
            // 多选
            selectionChangeHandler(val) {
                this.dataListSelections = val;
            },
            // 添加/修改
            addOrUpdateHandler(id, type) {
                this.showFlag = false;
                this.addOrUpdateFlag = true;
                this.crossAddOrUpdateFlag = false;
                if (type != 'info') {
                    type = 'else';
                }
                this.$nextTick(() => {
                    this.$refs.addOrUpdate.init(id, type);
                });
            },
            // 下载
            download(file) {
                window.open(" ${file} ")
            },
            // 删除
            deleteHandler(id) {
                var ids = id ? [Number(id)] : this.dataListSelections.map(item => {
                    return Number(item.id);
                });

                this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.$http({
                        url: "zhongchou/delete",
                        method: "post",
                        data: ids
                    }).then(({data}) => {
                        if(data && data.code === 0){
                            this.$message({
                                message: "操作成功",
                                type: "success",
                                duration: 1500,
                                onClose: () => {
                                    this.search();
                                }
                            });
                        }else{
                            this.$message.error(data.msg);
                        }
                    });
                });
            },
            // 导入功能上传文件成功后调用导入方法
            zhongchouUploadSuccess(data){
                let _this = this;
                _this.$http({
                    url: "zhongchou/batchInsert?fileName=" + data.file,
                    method: "get"
                }).then(({data}) => {
                    if(data && data.code === 0){
                        _this.$message({
                            message: "导入众筹数据成功",
                            type: "success",
                            duration: 1500,
                            onClose: () => {
                                _this.search();
                            }
                        });
                    }else{
                        _this.$message.error(data.msg);
                    }
                });

            },
            // 导入功能上传文件失败后调用导入方法
            zhongchouUploadError(data){
                this.$message.error('上传失败');
            },
            //增加库存
			plusZhongchouKucunNumber(thisId,zhongchouKucunNumber){
			//清空数据
				//库存数据设置为0
				this.kucunNumber = 0
				//id清空
				this.kucunNumberId = null
				//库存数据设置为0
				this.kucunNumber = 0
				//操作类型清空
				this.kucunNumberTypes = null
				//原有数量清空
				this.zhongchouKucunNumber = 0
			//判断数据是否为空	
				if(thisId == null || thisId=="" || thisId=="null"){
					this.$message.error('未知错误,请联系管理员处理');
					return;
				}
			//数据不为空放值
				//id
				this.kucunNumberId = thisId
				//操作类型
				this.kucunNumberTypes = "+"
				//原有数量
				this.zhongchouKucunNumber = zhongchouKucunNumber
				//将模态框打开
				this.zhongchouKucunNumberVisible = true;
				//标题
				this.zhongchouKucunNumberTitle = '增加库存'
			},
			//减少库存
			reduceZhongchouKucunNumber(thisId,zhongchouKucunNumber){
			//清空数据
				//库存数据设置为0
				this.kucunNumber = 0
				//id清空
				this.kucunNumberId = null
				//操作类型清空
				this.kucunNumberTypes = null
				//原有数量清空
				this.zhongchouKucunNumber = 0
			//判断数据是否为空	
				if(thisId == null || thisId=="" || thisId=="null"){
					this.$message.error('未知错误,请联系管理员处理');
					return;
				}
			//数据不为空放值
				//id
				this.kucunNumberId = thisId
				//操作类型
				this.kucunNumberTypes = "-"
				//原有数量
				this.zhongchouKucunNumber = zhongchouKucunNumber
				//将模态框打开
				this.zhongchouKucunNumberVisible = true;
				//标题
				this.zhongchouKucunNumberTitle = '减少库存'
			},
			//提交用户操作库存数量
			addZhongchouKucunNumber(){
				var type = null
				var maxNumber = 0
				if(this.kucunNumberTypes == "+"){
					type = '增加'
					maxNumber = this.zhongchouKucunNumber + this.kucunNumber
				}else{
					type = '减少'
					if(this.zhongchouKucunNumber < this.kucunNumber || this.kucunNumber <= 0){
						this.$message.error('库存不足不能减少');
						return false;
					}else{
						maxNumber = this.zhongchouKucunNumber - this.kucunNumber
					}
				}
				let date = {
					"id":this.kucunNumberId,
					"zhongchouKucunNumber":maxNumber
				}
				this.$confirm(`确定要`+type+`物品数量`+this.kucunNumber+`个?`, "提示", {
				    confirmButtonText: "确定",
				    cancelButtonText: "取消",
				    type: "warning"
				}).then(() => {
				    this.$http({
				        url: "zhongchou/update",
				        method: "post",
				        data: date
				    }).then(({ data }) => {
				        if (data && data.code === 0) {
				            this.$message({
				                message: "操作成功",
				                type: "success",
				                duration: 1500,
				                onClose: () => {
				                    this.search();
				                }
				            });
				        } else {
				            this.$message.error(data.msg);
				        }
				    });
				});
				this.zhongchouKucunNumberVisible = false;
			},        }
    };
</script>
<style lang="scss" scoped>
.slt {
    margin: 0 !important;
    display: flex;
  }

  .ad {
    margin: 0 !important;
    display: flex;
  }

  .pages {
    & /deep/ el-pagination__sizes{
      & /deep/ el-input__inner {
        height: 22px;
        line-height: 22px;
      }
    }
  }


  .el-button+.el-button {
    margin:0;
  }

  .tables {
	& /deep/ .el-button--success {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 6px;
		background-color: rgba(121, 244, 164, 0.54);
	}

	& /deep/ .el-button--primary {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 6px;
		background-color: rgba(169, 115, 232, 0.65);
	}

	& /deep/ .el-button--danger {
		height: 40px;
		color: #333;
		font-size: 14px;
		border-width: 1px;
		border-style: solid;
		border-color: #DCDFE6;
		border-radius: 6px;
		background-color: rgba(241, 120, 186, 1);
	}

    & /deep/ .el-button {
      margin: 4px;
    }
  }
</style>


