$(function () {
    var mapChart = echarts.init(document.getElementById('echarts-map-chart'));
    $.get('https://geo.datav.aliyun.com/areas_v3/bound/440100_full.json', function (geoJson) {
        myChart.hideLoading();
        echarts.registerMap('gz', geoJson);
        var mapoption = {
                title: {
                    text: '广州市title text',
                    subtext: 'Data from Wikipedia',
                    sublink:
                        'http://zh.wikipedia.org/wiki/%E9%A6%99%E6%B8%AF%E8%A1%8C%E6%94%BF%E5%8D%80%E5%8A%83#cite_note-12'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}<br/>{c} (p / km2)'
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        dataView: { readOnly: false },
                        restore: {},
                        saveAsImage: {}
                    }
                },
                visualMap: {
                    min: 117,
                    max: 50000,
                    text: ['High', 'Low'],
                    realtime: false,
                    calculable: true,
                    inRange: {
                        color: ['lightskyblue', 'yellow', 'orangered']
                    }
                },
                series: [
                    {
                        name: '广州市series name',
                        type: 'map',
                        map: 'gz',
                        label: {
                            show: true
                        },
                        data: [
                            { name: '从化区', value: 20057.34 },
                            { name: '花都区', value: 15477.48 },
                            { name: '增城区', value: 31686.1 },
                            { name: '白云区', value: 6992.6 },
                            { name: '黄埔区', value: 44045.49 },
                            { name: '天河区', value: 40689.64 },
                            { name: '越秀区', value: 37659.78 },
                            { name: '荔湾区', value: 45180.97 },
                            { name: '海珠区', value: 55204.26 },
                            { name: '番禺区', value: 21900.9 },
                            { name: '南沙区', value: 4918.26 }
                        ],
                    }
                ]}
        mapChart.setOption(mapoption);
        $(window).resize(mapChart.resize);
        })
});
