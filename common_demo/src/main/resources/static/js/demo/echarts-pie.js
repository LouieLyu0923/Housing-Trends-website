$(function () {
    var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
    $.getJSON('/emp/getAll',function (data) {
        console.log(data)
        var pieoption = {
            title : {
                text: '各区房价对比',
                subtext: '动态展示',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:data.name
            },
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:data.value

                }
            ]
        };
        pieChart.setOption(pieoption);
        $(window).resize(pieChart.resize);
    });
});
