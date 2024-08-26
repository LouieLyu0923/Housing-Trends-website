$(function () {
    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    $.get('/weather/line',function (data) {
        var lineoption = {
            title : {
                text: '房价趋势图'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['x区','y区']
            },
            grid:{
                x:40,
                x2:40,
                y2:24
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : data.date
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel : {
                        formatter: '{value} °C'
                    }
                }
            ],
            series : [
                {
                    name:'x区',
                    type:'line',
                    data:data.max,
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name:'y区',
                    type:'line',
                    data:data.min,
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
        };
        lineChart.setOption(lineoption);
        $(window).resize(lineChart.resize);
    })
});
