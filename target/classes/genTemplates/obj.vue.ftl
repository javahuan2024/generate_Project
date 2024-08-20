<template>
    <div class="wrapper vertical">
        <a-card>
            <a-form-model layout="inline">
                <a-row type="flex">
                    <a-col :lg="6" :md="8">
                        <a-form-model-item label="设备名称" :labelCol="{ span: 6 }" :wrapper-col="{ span: 16 }" style="width: 100%">
                            <a-input v-model="filterForm.name" placeholder="请输入设备名称"  :allowClear="true"></a-input>
                        </a-form-model-item>
                    </a-col>

                    <a-col flex="auto" style="text-align: right; margin-left: auto">
                        <a-form-model-item>
                            <a-button type="primary" icon="search" @click="onFilter">查询</a-button>
                            <a-button icon="reload" class="margin-l15" @click="onReset">重置</a-button>
                            <a-button type="link" class="margin-l15" @click="filterMore = false" v-if="filterMore">收起<a-icon type="up" /></a-button>
                            <a-button type="link" class="margin-l15" @click="filterMore = true" v-else>展开<a-icon type="down" /></a-button>
                        </a-form-model-item>
                    </a-col>
                </a-row>
            </a-form-model>
        </a-card>
        <a-card style="margin-top: 10px; flex: 1; height: 0" class="scroll-card">
            <div slot="title" class="display-f align-items-c">
                <span class="iconfont icon-tool-hardware"></span>
                <span class="margin-l5">设备表</span>
            </div>
            <div slot="extra">
                <a-button type="primary" icon="plus" @click="openCreate('new')">新增</a-button>
            </div>
            <epro-table
                    :columns="columns"
                    :data="tableData"
                    :loading="tableLoading"
                    ref="eproTable"
                    @change="pageChange"
                    v-if="tableData && tableData.records"
            >
                <template slot="id" slot-scope="scope">
                    <div class="">
                        <span class="link" @click="openCreate('edit',scope.record)">编辑</span>
                        <div role="separator" class="ant-divider ant-divider-vertical"></div>
                        <span class="link" style="color:#ff0000" @click="delItem(scope.record)">删除</span>
                    </div>
                </template>
                <template slot="isOnsale" slot-scope="scope">
                    {{ scope.text ? '启用':'禁用' }}
                </template>
            </epro-table>
        </a-card>
        <a-drawer
                placement="right"
                :visible="createShow"
                @close="closeCreate"
                width="700px"
                :maskClosable="false"
                class="drawer"
        >
            <div slot="title" class="display-f align-items-c">
                <span class="iconfont icon-tool-hardware"></span>
                <span class="margin-l5">{{ form.id ? '编辑设备表':'新增设备表' }}</span>
            </div>
            <div class="drawer-cot">
                <a-form-model
                        ref="form"
                        labelAlign="right"
                        :label-col="{ span: 8 }"
                        :wrapper-col="{ span: 16 }"
                        :model="form"
                        :rules="rules"
                        class="drawer-form"
                >
                    <div class="overflow-h">
                        <a-row>
                            <a-col span="12">
                                <a-form-model-item label="设备名称" prop="name">
                                    <a-input placeholder="请输入设备名称" v-model="form.name"/>
                                </a-form-model-item>
                            </a-col>
                            <a-col span="12">
                                <a-form-model-item label="设备序列号" prop="imei">
                                    <a-input placeholder="请输入设备序列号" v-model="form.imei"/>
                                </a-form-model-item>
                            </a-col>
                        </a-row>
                        <a-row>
                            <a-col span="12">
                                <a-form-model-item label="iccid号" prop="iccid">
                                    <a-input placeholder="请输入物联网卡iccid号" v-model="form.iccid"/>
                                </a-form-model-item>
                            </a-col>
                            <a-col span="12">
                                <a-form-model-item label="设备版本号" prop="version">
                                    <a-input placeholder="请输入设备序列号" v-model="form.version"/>
                                </a-form-model-item>
                            </a-col>
                        </a-row>
                        <a-row>
                            <a-col span="12">
                                <a-form-model-item label="设备品牌" prop="brand">
                                    <a-input placeholder="请输入设备品牌" v-model="form.brand"/>
                                </a-form-model-item>
                            </a-col>
                        </a-row>
                    </div>
                </a-form-model>
                <div class="drawer-handle">
                    <a-button type="primary" icon="check" class="width-80" @click="onSubmit">确定</a-button>
                    <a-button type="default" icon="close" class="width-80 margin-l15" @click="closeCreate">取消</a-button>
                </div>
            </div>
        </a-drawer>
    </div>
</template>

<script>
import { saveData, deleteData, getList } from '@/api/system/device'
export default {
    name: 'device',
    data () {
        return {
            filterForm: {
                pageSize: 20,
                pageNo: 1,
                name: '',
                'name-op': 'ct',
            },
            columns: [
                {
                    title: '设备名称',
                    dataIndex: 'name'
                },
                {
                    title: '设备序列号',
                    dataIndex: 'imei'
                },
                {
                    title: '品牌',
                    dataIndex: 'brand'
                },
                {
                    title: '是否启用',
                    dataIndex: 'isOnsale',
                    scopedSlots: { customRender: 'isOnsale' }
                },
                {
                    title: '物联网卡iccid号',
                    dataIndex: 'iccid',
                    width: 150
                },
                {
                    title: '设备版本号',
                    dataIndex: 'version'
                },
                {
                    title: '操作',
                    dataIndex: 'id',
                    scopedSlots: { customRender: 'id' }
                },
            ],
            tableData: {},
            tableLoading: false,
            form: {},
            rules: {
                // name: [
                //     { required: true, message: '请输入设备名称', trigger: 'blur' }
                // ],
                // imei: [
                //     { required: true, message: '请输入设备序列号', trigger: 'blur' }
                // ],
            },
            createShow: false,
            filterMore: false
        }
    },
    mounted () {
        this.getList()
    },
    methods: {
        getList () {
            this.tableLoading = true
            getList(this.filterForm).then(res => {
                this.tableData = res.data
                this.tableLoading = false
            })
        },
        onFilter () {
            this.filterForm.pageNo = 1
            this.$refs.eproTable.reload()
            this.getList()
        },
        onReset () {
            this.filterForm = {
                pageSize: 20,
                pageNo: 1
            }
            this.$refs.eproTable.reload()
            this.getList()
        },
        pageChange ({current, pageSize}) {
            this.filterForm.pageSize = pageSize
            this.filterForm.pageNo = current
            this.getList()
        },
        openCreate (type,item) {
            if (item) this.form = { ...item }
            this.createShow = true
        },
        closeCreate () {
            this.form = {}
            this.createShow = false
        },
        delItem (item) {
            this.$confirm({
                title: '提示',
                content: '确认删除这条数据？',
                centered: true,
                onOk: () => {
                    deleteData({
                        id: item.id
                    }).then(() => {
                        this.$message.success('删除成功！')
                        this.getList()
                    })
                }
            })
        },
        onSubmit () {
            this.$refs.form.validate(valid => {
                if (valid) {
                    saveData(this.form).then(() => {
                        this.$message.success('操作成功！')
                        this.getList()
                        this.closeCreate()
                    })
                }
            })
        },
        fileChange (file) {
            console.log(file)
        }
    }
}
</script>

<style lang="scss">

</style>

