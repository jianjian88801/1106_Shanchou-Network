const base = {
    get() {
        return {
            url : "http://localhost:8080/shanchouwang/",
            name: "shanchouwang",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/shanchouwang/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "善筹网"
        } 
    }
}
export default base
