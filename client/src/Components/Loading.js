const Loading = (props) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    xmlnsXlink="http://www.w3.org/1999/xlink"
    style={{
      margin: 'auto',
      background: 'rgba(255, 255, 255, 0)',
      display: 'block',
      shapeRendering: 'auto',
    }}
    width="200px"
    height="200px"
    viewBox="0 0 100 100"
    preserveAspectRatio="xMidYMid"
    {...props}
  >
    <rect x={18.5} y={28.5} width={13} height={43} fill="#281300">
      <animate
        attributeName="y"
        repeatCount="indefinite"
        dur="0.9900990099009901s"
        calcMode="spline"
        keyTimes="0;0.5;1"
        values="17.75;28.5;28.5"
        keySplines="0 0.5 0.5 1;0 0.5 0.5 1"
        begin="-0.19801980198019803s"
      />
      <animate
        attributeName="height"
        repeatCount="indefinite"
        dur="0.9900990099009901s"
        calcMode="spline"
        keyTimes="0;0.5;1"
        values="64.5;43;43"
        keySplines="0 0.5 0.5 1;0 0.5 0.5 1"
        begin="-0.19801980198019803s"
      />
    </rect>
    <rect x={43.5} y={28.5} width={13} height={43} fill="#e26c00">
      <animate
        attributeName="y"
        repeatCount="indefinite"
        dur="0.9900990099009901s"
        calcMode="spline"
        keyTimes="0;0.5;1"
        values="20.4375;28.5;28.5"
        keySplines="0 0.5 0.5 1;0 0.5 0.5 1"
        begin="-0.09900990099009901s"
      />
      <animate
        attributeName="height"
        repeatCount="indefinite"
        dur="0.9900990099009901s"
        calcMode="spline"
        keyTimes="0;0.5;1"
        values="59.125;43;43"
        keySplines="0 0.5 0.5 1;0 0.5 0.5 1"
        begin="-0.09900990099009901s"
      />
    </rect>
    <rect x={68.5} y={28.5} width={13} height={43} fill="#281300">
      <animate
        attributeName="y"
        repeatCount="indefinite"
        dur="0.9900990099009901s"
        calcMode="spline"
        keyTimes="0;0.5;1"
        values="20.4375;28.5;28.5"
        keySplines="0 0.5 0.5 1;0 0.5 0.5 1"
      />
      <animate
        attributeName="height"
        repeatCount="indefinite"
        dur="0.9900990099009901s"
        calcMode="spline"
        keyTimes="0;0.5;1"
        values="59.125;43;43"
        keySplines="0 0.5 0.5 1;0 0.5 0.5 1"
      />
    </rect>
  </svg>
);

export default Loading;
