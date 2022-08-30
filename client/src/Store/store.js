import create from 'zustand';

const useStore = create((set) => ({
  isLoginMode: false,
  setLoginMode: (value) => {
    set(() => ({ isLoginMode: value }));
  },
}));

export default useStore;
