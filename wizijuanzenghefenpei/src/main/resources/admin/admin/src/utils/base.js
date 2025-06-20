const base = {
    get() {
        return {
            url : "http://localhost:8080/wizijuanzenghefenpei/",
            name: "wizijuanzenghefenpei",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/wizijuanzenghefenpei/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "疫情物资捐赠和分配系统"
        } 
    }
}
export default base
