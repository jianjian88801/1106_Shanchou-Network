<template>
    <div class="addEdit-block">
        <el-form
                class="detail-form-content"
                ref="ruleForm"
                :model="ruleForm"
                :rules="rules"
                label-width="80px"
                :style="{backgroundColor:addEditForm.addEditBoxColor}">
            <el-row>
                <el-col :span="12"  v-if="sessionTable !='shangpin'">
                    <el-form-item class="select" v-if="type!='info'"  label="商品" prop="shangpinId">
                        <el-select v-model="ruleForm.shangpinId" :disabled="ro.shangpinId" filterable placeholder="请选择商品" @change="shangpinChange">
                            <el-option
                                    v-for="(item,index) in shangpinOptions"
                                    v-bind:key="item.id"
                                    :label="item.shangpinName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='shangpin' ">
                    <el-form-item class="input" v-if="type!='info'"  label="商品编号" prop="shangpinUuidNumber">
                        <el-input v-model="shangpinForm.shangpinUuidNumber"
                                  placeholder="商品编号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="商品编号" prop="shangpinUuidNumber">
                            <el-input v-model="ruleForm.shangpinUuidNumber"
                                      placeholder="商品编号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='shangpin' ">
                    <el-form-item class="input" v-if="type!='info'"  label="商品名称" prop="shangpinName">
                        <el-input v-model="shangpinForm.shangpinName"
                                  placeholder="商品名称" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="商品名称" prop="shangpinName">
                            <el-input v-model="ruleForm.shangpinName"
                                      placeholder="商品名称" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24" v-if="sessionTable !='shangpin' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.shangpinPhoto" label="商品照片" prop="shangpinPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (shangpinForm.shangpinPhoto || '').split(',')" :src="item" width="100" height="100">
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.shangpinPhoto" label="商品照片" prop="shangpinPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.shangpinPhoto || '').split(',')" :src="item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='shangpin' ">
                    <el-form-item class="input" v-if="type!='info'"  label="商品类型" prop="shangpinValue">
                        <el-input v-model="shangpinForm.shangpinValue"
                                  placeholder="商品类型" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="商品类型" prop="shangpinValue">
                            <el-input v-model="ruleForm.shangpinValue"
                                      placeholder="商品类型" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='shangpin' ">
                    <el-form-item class="input" v-if="type!='info'"  label="商品现价" prop="shangpinNewMoney">
                        <el-input v-model="shangpinForm.shangpinNewMoney"
                                  placeholder="商品现价" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="商品现价" prop="shangpinOldMoney">
                            <el-input v-model="ruleForm.shangpinNewMoney"
                                      placeholder="商品现价" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yonghu'">
                    <el-form-item class="select" v-if="type!='info'"  label="用户" prop="yonghuId">
                        <el-select v-model="ruleForm.yonghuId" :disabled="ro.yonghuId" filterable placeholder="请选择用户" @change="yonghuChange">
                            <el-option
                                    v-for="(item,index) in yonghuOptions"
                                    v-bind:key="item.id"
                                    :label="item.yonghuName"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-col :span="12"  v-if="sessionTable !='yonghu' ">
                    <el-form-item class="input" v-if="type!='info'"  label="用户姓名" prop="yonghuName">
                        <el-input v-model="yonghuForm.yonghuName"
                                  placeholder="用户姓名" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="用户姓名" prop="yonghuName">
                            <el-input v-model="ruleForm.yonghuName"
                                      placeholder="用户姓名" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="12"  v-if="sessionTable !='yonghu' ">
                    <el-form-item class="input" v-if="type!='info'"  label="用户手机号" prop="yonghuPhone">
                        <el-input v-model="yonghuForm.yonghuPhone"
                                  placeholder="用户手机号" clearable readonly></el-input>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="用户手机号" prop="yonghuPhone">
                            <el-input v-model="ruleForm.yonghuPhone"
                                      placeholder="用户手机号" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24" v-if="sessionTable !='yonghu' ">
                    <el-form-item class="upload" v-if="type!='info' && !ro.yonghuPhoto" label="用户头像" prop="yonghuPhoto">
                        <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (yonghuForm.yonghuPhoto || '').split(',')" :src="item" width="100" height="100">
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.yonghuPhoto" label="用户头像" prop="yonghuPhoto">
                            <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in (ruleForm.yonghuPhoto || '').split(',')" :src="item" width="100" height="100">
                        </el-form-item>
                    </div>
                </el-col>
                <input id="updateId" name="id" type="hidden">
            <input id="yonghuId" name="yonghuId" type="hidden">
            <input id="shangpinId" name="shangpinId" type="hidden">
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="众筹编号" prop="zhongchouUuidNumber">
                       <el-input v-model="ruleForm.zhongchouUuidNumber"
                                 placeholder="众筹编号" clearable  :readonly="ro.zhongchouUuidNumber"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="众筹编号" prop="zhongchouUuidNumber">
                           <el-input v-model="ruleForm.zhongchouUuidNumber"
                                     placeholder="众筹编号" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="众筹名称" prop="zhongchouName">
                       <el-input v-model="ruleForm.zhongchouName"
                                 placeholder="众筹名称" clearable  :readonly="ro.zhongchouName"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="众筹名称" prop="zhongchouName">
                           <el-input v-model="ruleForm.zhongchouName"
                                     placeholder="众筹名称" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12">
                    <el-form-item class="select" v-if="type!='info'"  label="众筹类型" prop="zhongchouTypes">
                        <el-select v-model="ruleForm.zhongchouTypes" :disabled="ro.zhongchouTypes" placeholder="请选择众筹类型">
                            <el-option
                                v-for="(item,index) in zhongchouTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="众筹类型" prop="zhongchouValue">
                        <el-input v-model="ruleForm.zhongchouValue"
                            placeholder="众筹类型" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="众筹数量" prop="zhongchouKucunNumber">
                       <el-input v-model="ruleForm.zhongchouKucunNumber"
                                 placeholder="众筹数量" clearable  :readonly="ro.zhongchouKucunNumber"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="众筹数量" prop="zhongchouKucunNumber">
                           <el-input v-model="ruleForm.zhongchouKucunNumber"
                                     placeholder="众筹数量" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="已筹金额" prop="yichouMoney">
                       <el-input v-model="ruleForm.yichouMoney"
                                 placeholder="已筹金额" clearable  :readonly="ro.yichouMoney"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="已筹金额" prop="yichouMoney">
                           <el-input v-model="ruleForm.yichouMoney"
                                     placeholder="已筹金额" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="众筹总额" prop="zhongchouMoney">
                       <el-input v-model="totalMoney"
                                 placeholder="众筹总额" clearable  :readonly="ro.zhongchouMoney"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="众筹总额" prop="zhongchouMoney">
                           <el-input v-model="ruleForm.zhongchouMoney"
                                     placeholder="众筹总额" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12" v-if="sessionTable !='yonghu' && type!='info'">
                    <el-form-item class="select" v-if="type!='info'"  label="众筹状态" prop="zhongchouZhuangtaiTypes">
                        <el-select v-model="ruleForm.zhongchouZhuangtaiTypes" :disabled="ro.zhongchouZhuangtaiTypes" placeholder="请选择众筹状态">
                            <el-option
                                v-for="(item,index) in zhongchouZhuangtaiTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="众筹状态" prop="zhongchouZhuangtaiValue">
                        <el-input v-model="ruleForm.zhongchouZhuangtaiValue"
                            placeholder="众筹状态" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="收件人姓名" prop="zhongchouChenggongName">
                       <el-input v-model="ruleForm.zhongchouChenggongName"
                                 placeholder="收件人姓名" clearable  :readonly="ro.zhongchouChenggongName"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="收件人姓名" prop="zhongchouChenggongName">
                           <el-input v-model="ruleForm.zhongchouChenggongName"
                                     placeholder="收件人姓名" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="收件人联系方式" prop="zhongchouChenggongPhone">
                       <el-input v-model="ruleForm.zhongchouChenggongPhone"
                                 placeholder="收件人联系方式" clearable  :readonly="ro.zhongchouChenggongPhone"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="收件人联系方式" prop="zhongchouChenggongPhone">
                           <el-input v-model="ruleForm.zhongchouChenggongPhone"
                                     placeholder="收件人联系方式" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12">
                   <el-form-item class="input" v-if="type!='info'"  label="收件人地址" prop="zhongchouChenggongAddress">
                       <el-input v-model="ruleForm.zhongchouChenggongAddress"
                                 placeholder="收件人地址" clearable  :readonly="ro.zhongchouChenggongAddress"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="收件人地址" prop="zhongchouChenggongAddress">
                           <el-input v-model="ruleForm.zhongchouChenggongAddress"
                                     placeholder="收件人地址" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12" v-if="sessionTable !='yonghu' && type!='info'">
                   <el-form-item class="input" v-if="type!='info'"  label="快递名称" prop="zhongchouChenggongKuaidiname">
                       <el-input v-model="ruleForm.zhongchouChenggongKuaidiname"
                                 placeholder="快递名称" clearable  :readonly="ro.zhongchouChenggongKuaidiname"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="快递名称" prop="zhongchouChenggongKuaidiname">
                           <el-input v-model="ruleForm.zhongchouChenggongKuaidiname"
                                     placeholder="快递名称" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
               <el-col :span="12" v-if="sessionTable !='yonghu' && type!='info'">
                   <el-form-item class="input" v-if="type!='info'"  label="快递号" prop="zhongchouChenggongKuaidiaddress">
                       <el-input v-model="ruleForm.zhongchouChenggongKuaidiaddress"
                                 placeholder="快递号" clearable  :readonly="ro.zhongchouChenggongKuaidiaddress"></el-input>
                   </el-form-item>
                   <div v-else>
                       <el-form-item class="input" label="快递号" prop="zhongchouChenggongKuaidiaddress">
                           <el-input v-model="ruleForm.zhongchouChenggongKuaidiaddress"
                                     placeholder="快递号" readonly></el-input>
                       </el-form-item>
                   </div>
               </el-col>
                <el-col :span="12" v-if="sessionTable !='yonghu' && type!='info'">
                    <el-form-item class="select" v-if="type!='info'"  label="快递状态" prop="zhongchouKuaidiTypes">
                        <el-select v-model="ruleForm.zhongchouKuaidiTypes" :disabled="ro.zhongchouKuaidiTypes" placeholder="请选择快递状态">
                            <el-option
                                v-for="(item,index) in zhongchouKuaidiTypesOptions"
                                v-bind:key="item.codeIndex"
                                :label="item.indexName"
                                :value="item.codeIndex">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <div v-else>
                        <el-form-item class="input" label="快递状态" prop="zhongchouKuaidiValue">
                        <el-input v-model="ruleForm.zhongchouKuaidiValue"
                            placeholder="快递状态" readonly></el-input>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  label="众筹详情" prop="zhongchouContent">
                        <editor style="min-width: 200px; max-width: 600px;"
                                v-model="ruleForm.zhongchouContent"
                                class="editor"
                                action="file/upload">
                        </editor>
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.zhongchouContent" label="众筹详情" prop="zhongchouContent">
                            <span v-html="ruleForm.zhongchouContent"></span>
                        </el-form-item>
                    </div>
                </el-col>
                <el-col :span="24">
                    <el-form-item v-if="type!='info'"  class="input" label="众筹截止时间" prop="jieshuTime">
                        <el-date-picker
                                value-format="yyyy-MM-dd HH:mm:ss"
                                v-model="ruleForm.jieshuTime"
                                type="datetime"
                                placeholder="众筹截止时间"
                                :disabled="ro.jieshuTime">
                        </el-date-picker>
                    </el-form-item>
                    <div v-else>
                        <el-form-item v-if="ruleForm.jieshuTime" label="众筹截止时间" prop="jieshuTime">
                            <span v-html="ruleForm.jieshuTime"></span>
                        </el-form-item>
                    </div>
                </el-col>

            </el-row>
            <el-form-item class="btn">
                <el-button v-if="type!='info'" type="primary" class="btn-success" @click="onSubmit">提交</el-button>
                <el-button v-if="type!='info'" class="btn-close" @click="back()">取消</el-button>
                <el-button v-if="type=='info'" class="btn-close" @click="back()">返回</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import styleJs from "../../../utils/style.js";
    // 数字，邮件，手机，url，身份证校验
    import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
    export default {
        data() {
            return {
                addEditForm:null,
                id: '',
                type: '',
                sessionTable : "",//登录账户所在表名
                role : "",//权限
                userId:"",//当前登录人的id
                shangpinForm: {},
                yonghuForm: {},
                ro:{
                    yonghuId: false,
                    shangpinId: false,
                    zhongchouUuidNumber: false,
                    zhongchouName: false,
                    zhongchouTypes: false,
                    zhongchouKucunNumber: false,
                    yichouMoney: false,
                    zhongchouMoney: false,
                    zhongchouClicknum: false,
                    zhongchouZhuangtaiTypes: false,
                    zhongchouChenggongName: false,
                    zhongchouChenggongPhone: false,
                    zhongchouChenggongAddress: false,
                    zhongchouChenggongKuaidiname: false,
                    zhongchouChenggongKuaidiaddress: false,
                    zhongchouKuaidiTypes: false,
                    zhongchouContent: false,
                    insertTime: false,
                    jieshuTime: false,
                    zhongchouDelete: false,
                },
                ruleForm: {
                    yonghuId: '',
                    shangpinId: '',
                    zhongchouUuidNumber: new Date().getTime(),
                    zhongchouName: '',
                    zhongchouTypes: '',
                    zhongchouKucunNumber: 1,
                    yichouMoney: 0,
                    zhongchouMoney: '',
                    zhongchouClicknum: '',
                    zhongchouZhuangtaiTypes: '',
                    zhongchouChenggongName: '',
                    zhongchouChenggongPhone: '',
                    zhongchouChenggongAddress: '',
                    zhongchouChenggongKuaidiname: '',
                    zhongchouChenggongKuaidiaddress: '',
                    zhongchouKuaidiTypes: '',
                    zhongchouContent: '',
                    insertTime: '',
                    jieshuTime: '',
                    zhongchouDelete: '',
                },
                zhongchouTypesOptions : [],
                zhongchouZhuangtaiTypesOptions : [],
                zhongchouKuaidiTypesOptions : [],
                shangpinOptions : [],
                yonghuOptions : [],
                rules: {
                   yonghuId: [
                              { required: true, message: '用户不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   shangpinId: [
                              { required: true, message: '商品不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   zhongchouUuidNumber: [
                              { required: true, message: '众筹编号不能为空', trigger: 'blur' },
                          ],
                   zhongchouName: [
                              { required: true, message: '众筹名称不能为空', trigger: 'blur' },
                          ],
                   zhongchouTypes: [
                              { required: true, message: '众筹类型不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   zhongchouKucunNumber: [
                              { required: true, message: '众筹数量不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   yichouMoney: [
                              { required: true, message: '已筹金额不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]{0,6}(\.[0-9]{1,2})?$/,
                                  message: '只允许输入整数6位,小数2位的数字',
                                  trigger: 'blur'
                              }
                          ],
                   /*zhongchouMoney: [
                              { required: true, message: '众筹总额不能为空', trigger: 'blur' },
                              {  pattern: /^[0-9]{0,6}(\.[0-9]{1,2})?$/,
                                  message: '只允许输入整数6位,小数2位的数字',
                                  trigger: 'blur'
                              }
                          ],*/
                   zhongchouClicknum: [
                              { required: true, message: '热度不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   zhongchouZhuangtaiTypes: [
                              { required: true, message: '众筹状态不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   zhongchouChenggongName: [
                              { required: true, message: '收件人姓名不能为空', trigger: 'blur' },
                          ],
                   zhongchouChenggongPhone: [
                              { required: true, message: '收件人联系方式不能为空', trigger: 'blur' },
                          ],
                   zhongchouChenggongAddress: [
                              { required: true, message: '收件人地址不能为空', trigger: 'blur' },
                          ],
                   zhongchouChenggongKuaidiname: [
                              { required: true, message: '快递名称不能为空', trigger: 'blur' },
                          ],
                   zhongchouChenggongKuaidiaddress: [
                              { required: true, message: '快递号不能为空', trigger: 'blur' },
                          ],
                   zhongchouKuaidiTypes: [
                              { required: true, message: '快递状态不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                   zhongchouContent: [
                              { required: true, message: '众筹详情不能为空', trigger: 'blur' },
                          ],
                   insertTime: [
                              { required: true, message: '发起时间不能为空', trigger: 'blur' },
                          ],
                   jieshuTime: [
                              { required: true, message: '众筹截止时间不能为空', trigger: 'blur' },
                          ],
                   zhongchouDelete: [
                              { required: true, message: '逻辑删除不能为空', trigger: 'blur' },
                              {  pattern: /^[1-9][0-9]*$/,
                                  message: '只允许输入整数',
                                  trigger: 'blur'
                              }
                          ],
                }
            };
        },
        props: ["parent"],
        computed: {
            totalMoney: function() {
                var total = 0;


                total = this.shangpinForm.shangpinNewMoney * this.ruleForm.zhongchouKucunNumber;
                // for (var item of this.dataList) {
                //     total += item.${temp333333}NewMoney * item.buyNumber
                // }
                return total;
            }
        },
        created() {
            //获取当前登录用户的信息
            this.sessionTable = this.$storage.get("sessionTable");
            this.role = this.$storage.get("role");
            this.userId = this.$storage.get("userId");


            if (this.role != "管理员"){
                this.ro.yichouMoney = true;
                this.ro.zhongchouMoney = true;
            }
            this.addEditForm = styleJs.addStyle();
            this.addEditStyleChange()
            this.addEditUploadStyleChange()
            //获取下拉框信息
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhongchou_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.zhongchouTypesOptions = data.data.list;
                    }
                });
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhongchou_zhuangtai_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.zhongchouZhuangtaiTypesOptions = data.data.list;
                    }
                });
                this.$http({
                    url:`dictionary/page?page=1&limit=100&sort=&order=&dicCode=zhongchou_kuaidi_types`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.zhongchouKuaidiTypesOptions = data.data.list;
                    }
                });

         this.$http({
             url: `shangpin/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.shangpinOptions = data.data.list;
            }
         });
         this.$http({
             url: `yonghu/page?page=1&limit=100`,
             method: "get"
         }).then(({ data }) => {
             if (data && data.code === 0) {
                this.yonghuOptions = data.data.list;
            }
         });

        },
        mounted() {
        },
        methods: {
            // 下载
            download(file){
                window.open(`${file}`)
            },
            // 初始化
            init(id,type) {
                if (id) {
                    this.id = id;
                    this.type = type;
                }
                if(this.type=='info'||this.type=='else'){
                    this.info(id);
                }
                // 获取用户信息
                this.$http({
                    url:`${this.$storage.get("sessionTable")}/session`,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        var json = data.data;
                    } else {
                        this.$message.error(data.msg);
                    }
                });
            },
            shangpinChange(id){
                this.$http({
                    url: `shangpin/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.shangpinForm = data.data;
                    }
                });
            },
            yonghuChange(id){
                this.$http({
                    url: `yonghu/info/`+id,
                    method: "get"
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        this.yonghuForm = data.data;
                    }
                });
            },
            // 多级联动参数
            info(id) {
                let _this =this;
                _this.$http({
                    url: `zhongchou/info/${id}`,
                    method: 'get'
                }).then(({ data }) => {
                    if (data && data.code === 0) {
                        _this.ruleForm = data.data;
                        _this.shangpinChange(data.data.shangpinId)
                        _this.yonghuChange(data.data.yonghuId)
                    } else {
                        _this.$message.error(data.msg);
                    }
                });
            },
            // 提交
            onSubmit() {
                this.$refs["ruleForm"].validate(valid => {
                    if (valid) {
                        this.$http({
                            url:`zhongchou/${!this.ruleForm.id ? "save" : "update"}`,
                            method: "post",
                            data: this.ruleForm
                        }).then(({ data }) => {
                            if (data && data.code === 0) {
                                this.$message({
                                    message: "操作成功",
                                    type: "success",
                                    duration: 1500,
                                    onClose: () => {
                                        this.parent.showFlag = true;
                                        this.parent.addOrUpdateFlag = false;
                                        this.parent.zhongchouCrossAddOrUpdateFlag = false;
                                        this.parent.search();
                                        this.parent.contentStyleChange();
                                    }
                                });
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            },
            // 获取uuid
            getUUID () {
                return new Date().getTime();
            },
            // 返回
            back() {
                this.parent.showFlag = true;
                this.parent.addOrUpdateFlag = false;
                this.parent.zhongchouCrossAddOrUpdateFlag = false;
                this.parent.contentStyleChange();
            },
            //图片

            addEditStyleChange() {
                this.$nextTick(()=>{
                    // input
                    document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputFontColor
                        el.style.fontSize = this.addEditForm.inputFontSize
                        el.style.borderWidth = this.addEditForm.inputBorderWidth
                        el.style.borderStyle = this.addEditForm.inputBorderStyle
                        el.style.borderColor = this.addEditForm.inputBorderColor
                        el.style.borderRadius = this.addEditForm.inputBorderRadius
                        el.style.backgroundColor = this.addEditForm.inputBgColor
                    })
                    document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.inputHeight
                        el.style.color = this.addEditForm.inputLableColor
                        el.style.fontSize = this.addEditForm.inputLableFontSize
                    })
                    // select
                    document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectFontColor
                        el.style.fontSize = this.addEditForm.selectFontSize
                        el.style.borderWidth = this.addEditForm.selectBorderWidth
                        el.style.borderStyle = this.addEditForm.selectBorderStyle
                        el.style.borderColor = this.addEditForm.selectBorderColor
                        el.style.borderRadius = this.addEditForm.selectBorderRadius
                        el.style.backgroundColor = this.addEditForm.selectBgColor
                    })
                    document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.selectHeight
                        el.style.color = this.addEditForm.selectLableColor
                        el.style.fontSize = this.addEditForm.selectLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el=>{
                        el.style.color = this.addEditForm.selectIconFontColor
                        el.style.fontSize = this.addEditForm.selectIconFontSize
                    })
                    // date
                    document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el=>{
                        el.style.height = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateFontColor
                        el.style.fontSize = this.addEditForm.dateFontSize
                        el.style.borderWidth = this.addEditForm.dateBorderWidth
                        el.style.borderStyle = this.addEditForm.dateBorderStyle
                        el.style.borderColor = this.addEditForm.dateBorderColor
                        el.style.borderRadius = this.addEditForm.dateBorderRadius
                        el.style.backgroundColor = this.addEditForm.dateBgColor
                    })
                    document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.dateHeight
                        el.style.color = this.addEditForm.dateLableColor
                        el.style.fontSize = this.addEditForm.dateLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el=>{
                        el.style.color = this.addEditForm.dateIconFontColor
                        el.style.fontSize = this.addEditForm.dateIconFontSize
                        el.style.lineHeight = this.addEditForm.dateHeight
                    })
                    // upload
                    let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
                    document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el=>{
                        el.style.lineHeight = this.addEditForm.uploadHeight
                        el.style.color = this.addEditForm.uploadLableColor
                        el.style.fontSize = this.addEditForm.uploadLableFontSize
                    })
                    document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el=>{
                        el.style.color = this.addEditForm.uploadIconFontColor
                        el.style.fontSize = this.addEditForm.uploadIconFontSize
                        el.style.lineHeight = iconLineHeight
                        el.style.display = 'block'
                    })
                    // 多文本输入框
                    document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el=>{
                        el.style.height = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaFontColor
                        el.style.fontSize = this.addEditForm.textareaFontSize
                        el.style.borderWidth = this.addEditForm.textareaBorderWidth
                        el.style.borderStyle = this.addEditForm.textareaBorderStyle
                        el.style.borderColor = this.addEditForm.textareaBorderColor
                        el.style.borderRadius = this.addEditForm.textareaBorderRadius
                        el.style.backgroundColor = this.addEditForm.textareaBgColor
                    })
                    document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el=>{
                        // el.style.lineHeight = this.addEditForm.textareaHeight
                        el.style.color = this.addEditForm.textareaLableColor
                        el.style.fontSize = this.addEditForm.textareaLableFontSize
                    })
                    // 保存
                    document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el=>{
                        el.style.width = this.addEditForm.btnSaveWidth
                        el.style.height = this.addEditForm.btnSaveHeight
                        el.style.color = this.addEditForm.btnSaveFontColor
                        el.style.fontSize = this.addEditForm.btnSaveFontSize
                        el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
                        el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
                        el.style.borderColor = this.addEditForm.btnSaveBorderColor
                        el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnSaveBgColor
                    })
                    // 返回
                    document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el=>{
                        el.style.width = this.addEditForm.btnCancelWidth
                        el.style.height = this.addEditForm.btnCancelHeight
                        el.style.color = this.addEditForm.btnCancelFontColor
                        el.style.fontSize = this.addEditForm.btnCancelFontSize
                        el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
                        el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
                        el.style.borderColor = this.addEditForm.btnCancelBorderColor
                        el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
                        el.style.backgroundColor = this.addEditForm.btnCancelBgColor
                    })
                })
            },
            addEditUploadStyleChange() {
                this.$nextTick(()=>{
                    document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el=>{
                        el.style.width = this.addEditForm.uploadHeight
                        el.style.height = this.addEditForm.uploadHeight
                        el.style.borderWidth = this.addEditForm.uploadBorderWidth
                        el.style.borderStyle = this.addEditForm.uploadBorderStyle
                        el.style.borderColor = this.addEditForm.uploadBorderColor
                        el.style.borderRadius = this.addEditForm.uploadBorderRadius
                        el.style.backgroundColor = this.addEditForm.uploadBgColor
                    })
                })
            },
        }
    };
</script>
<style lang="scss">
.editor{
  height: 500px;

  & /deep/ .ql-container {
	  height: 310px;
  }
}
.amap-wrapper {
  width: 100%;
  height: 500px;
}
.search-box {
  position: absolute;
}
.addEdit-block {
	margin: -10px;
}
.detail-form-content {
	padding: 12px;
}
.btn .el-button {
  padding: 0;
}</style>

