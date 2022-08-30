const Loading = (props) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    xmlnsXlink="http://www.w3.org/1999/xlink"
    style={{
      margin: 'auto',
      background: 'rgb(241, 242, 243)',
      display: 'block',
      shapeRendering: 'auto',
    }}
    width="234px"
    height="234px"
    viewBox="0 0 100 100"
    preserveAspectRatio="xMidYMid"
    {...props}
  >
    <g transform="translate(80,50)">
      <g transform="rotate(0)">
        <circle cx={0} cy={0} r={6} fill="#db7033" fillOpacity={1}>
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="-0.8571428571428571s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="-0.8571428571428571s"
          />
        </circle>
      </g>
    </g>
    <g transform="translate(68.704694055762,73.4549444740409)">
      <g transform="rotate(51.42857142857143)">
        <circle
          cx={0}
          cy={0}
          r={6}
          fill="#db7033"
          fillOpacity={0.8571428571428571}
        >
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="-0.7142857142857143s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="-0.7142857142857143s"
          />
        </circle>
      </g>
    </g>
    <g transform="translate(43.32437198131057,79.2478373654547)">
      <g transform="rotate(102.85714285714286)">
        <circle
          cx={0}
          cy={0}
          r={6}
          fill="#db7033"
          fillOpacity={0.7142857142857143}
        >
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="-0.5714285714285714s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="-0.5714285714285714s"
          />
        </circle>
      </g>
    </g>
    <g transform="translate(22.97093396292743,63.01651217352675)">
      <g transform="rotate(154.2857142857143)">
        <circle
          cx={0}
          cy={0}
          r={6}
          fill="#db7033"
          fillOpacity={0.5714285714285714}
        >
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="-0.42857142857142855s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="-0.42857142857142855s"
          />
        </circle>
      </g>
    </g>
    <g transform="translate(22.970933962927425,36.98348782647326)">
      <g transform="rotate(205.71428571428572)">
        <circle
          cx={0}
          cy={0}
          r={6}
          fill="#db7033"
          fillOpacity={0.42857142857142855}
        >
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="-0.2857142857142857s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="-0.2857142857142857s"
          />
        </circle>
      </g>
    </g>
    <g transform="translate(43.32437198131056,20.752162634545293)">
      <g transform="rotate(257.1428571428571)">
        <circle
          cx={0}
          cy={0}
          r={6}
          fill="#db7033"
          fillOpacity={0.2857142857142857}
        >
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="-0.14285714285714285s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="-0.14285714285714285s"
          />
        </circle>
      </g>
    </g>
    <g transform="translate(68.704694055762,26.545055525959103)">
      <g transform="rotate(308.5714285714286)">
        <circle
          cx={0}
          cy={0}
          r={6}
          fill="#db7033"
          fillOpacity={0.14285714285714285}
        >
          <animateTransform
            attributeName="transform"
            type="scale"
            begin="0s"
            values="1.6500000000000001 1.6500000000000001;1 1"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
          />
          <animate
            attributeName="fill-opacity"
            keyTimes="0;1"
            dur="1s"
            repeatCount="indefinite"
            values="1;0"
            begin="0s"
          />
        </circle>
      </g>
    </g>
  </svg>
);

export default Loading;
