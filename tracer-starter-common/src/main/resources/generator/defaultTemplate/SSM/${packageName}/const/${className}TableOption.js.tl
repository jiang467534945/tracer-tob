export const ${data.lowerClassName}Option = {
    border: true,

    selection: true,
    columnBtn:false,
    refreshBtn:false,
    addBtn:false,
    delBtn:false,
    editBtn:false,
    menuWidth:300,
    menuAlign:'center',
    formWidth: '60%',
    align:'center',
    stripe:true,
    column: [
    {
                label: "编号",
                prop: "id",

                hide:true,
                visdiplay:false,
                rules: [{
                    required: true,
                    message: "请输入编号",
                    trigger: "blur"
                }]
            },
    @for(c in data.columns){
        @if(isNotEmpty(c.simpleColumnComment)){
        /**${c.simpleColumnComment}*/
        @}
        {
        label:"${c.simpleColumnComment}",
        prop:"${c.property}",

            rules: [{
                   required: true,
                   message: "${c.simpleColumnComment}",
                   trigger: "blur"
               }]
        },
    @}
    ]

};

